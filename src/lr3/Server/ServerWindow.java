package lr3.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerWindow extends JFrame {
    private String title;

    private JTextArea textArea;
    private JButton button;

    private TextWriter textFieldWriter;

    public ServerWindow(String title)  {
        super(title);

        this.title = title;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLayout(new BorderLayout());

        this.setResizable(false);

        addControls();

        this.pack();

        this.setVisible(true);

        textArea.append("Starting server...\n");

        new MainServerThread(textFieldWriter).start();
    }

    private void addControls() {

        // up
        JPanel panel = new JPanel(true);

        panel.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setColumns(50);
        textArea.setRows(30);
        textArea.setEditable(false);
        textArea.setLineWrap(true);

        textFieldWriter = new TextWriter(this.textArea);

        panel.add(textArea, BorderLayout.WEST);

        JScrollPane scroll = new JScrollPane (textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        panel.add(scroll, BorderLayout.EAST);

        this.add(panel, BorderLayout.NORTH);

        // down
        JPanel panel2 = new JPanel(true);
        panel2.setLayout(new BorderLayout());

        button = new JButton("Start");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

//                textArea.append("Starting server...\n");
//
//                new MainServerThread(textFieldWriter).start();

            }
        });

        panel2.add(button, BorderLayout.CENTER);

        this.add(panel2, BorderLayout.SOUTH);
    }
}
