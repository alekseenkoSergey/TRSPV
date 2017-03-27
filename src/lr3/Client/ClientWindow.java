package lr3.Client;

import lr3.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.Random;

public class ClientWindow extends JFrame {
    private JTextArea textArea;
    private JButton button;
    private String title;
    private String login;
    private Socket socket;
    private JTextField textField;
    private ObjectOutputStream outputStream;

    public ClientWindow()  {
        super();

        login = randomString(new Random().nextInt(10)+5);
        this.title = "Client : " + login;

        this.setTitle(title);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        addControls();
        this.pack();

        this.setVisible(true);

        connect();

        new ClientListener(this).start();
    }

    public void printToTextArea(String message) {
        this.textArea.append(message);
    }

    public Socket getSocket() {
        return socket;
    }

    // установка соединения с сервером
    private boolean connect() {

        try {
            // содключаем сокет
            socket = new Socket("localhost", 1234);

            // создает объект для отправки сообщений
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());

            // установить соединение, отправить серверу сообщение о подключении
            outputStream.writeObject(new Message(login, ""));

        } catch (IOException e) {
            textArea.append("Couldn't connect to server!\n");
            return false;
        }

        return true;
    }

    private void addControls() {

        // up
        JPanel panel = new JPanel(true);

        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setColumns(50);
        textArea.setRows(20);
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        panel.add(textArea, BorderLayout.WEST);
        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scroll, BorderLayout.EAST);
        this.add(panel, BorderLayout.NORTH);

        // down
        JPanel panel2 = new JPanel(true);
        panel2.setLayout(new BorderLayout());

        // text field
        textField = new JTextField(46);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel2.add(textField, BorderLayout.WEST);

        // button
        button = new JButton("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        panel2.add(button, BorderLayout.EAST);
        this.add(panel2, BorderLayout.SOUTH);
    }

    // отправляет сообщение
    private void sendMessage() {
        Message message = new Message(login, textField.getText());
        try {
            outputStream.writeObject(message);
        } catch (IOException e1) {
            textArea.append("Couldn't send the message!\n");
        }
        finally {
            String messageString = generateMessageString(message);
            textArea.append(messageString);
            textField.setText("");
        }
    }

    private String generateMessageString(Message message) {
        return message.getLogin() + " [" + message.getDate() + "]: " + message.getMessage() + "\n";
    }

    // Генерирует случайную строку заданной длины для логина
    private String randomString( int len ){

        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

}
