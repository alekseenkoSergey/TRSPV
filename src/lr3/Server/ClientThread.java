package lr3.Server;

import lr3.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private Socket socket;
    private Message c;
    private String login;

    private TextWriter textFieldWriter;

    public ClientThread(Socket socket, TextWriter textFieldWriter) {
        this.socket = socket;
        this.textFieldWriter = textFieldWriter;
        this.start();
    }

    public void run() {
        System.out.println("new thread started for client!");
        try {
            final ObjectInputStream inputStream   = new ObjectInputStream(this.socket.getInputStream());

            this.c = (Message) inputStream.readObject();
            this.login = this.c.getLogin();

            textFieldWriter.appendText(login + " connected");

            while (true) {
                this.c = (Message) inputStream.readObject();

                textFieldWriter.appendText(c.getLogin() + " [" + c.getDate() + "]: " + c.getMessage());

            }

        } catch (SocketException e) {
            textFieldWriter.appendText(login + " disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
