package lr3.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MainServerThread extends Thread {

    private TextWriter textFieldWriter;

    public MainServerThread(TextWriter textFieldWriter) {
        this.textFieldWriter = textFieldWriter;
    }

    @Override
    public void run() {
        try {
            //Создаем слушатель
            ServerSocket socketListener = new ServerSocket(1234);

            while (true) {
                Socket client = null;
                while (client == null) {
                    client = socketListener.accept();
                }
                new ClientThread(client, textFieldWriter); //Создаем новый поток, которому передаем сокет
            }
        } catch (SocketException e) {
            System.err.println("Socket exception");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("I/O exception");
            e.printStackTrace();
        }
    }
}
