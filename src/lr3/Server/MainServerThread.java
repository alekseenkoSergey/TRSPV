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
            //Создаем слушателя
            ServerSocket socketListener = new ServerSocket(1234);

            // начинаем ждать клиентов в бесконеыном цикле
            while (true) {
                Socket client = null;
                while (client == null) {                    // если возник клиент
                    client = socketListener.accept();       // то принимаем соединение
                }
                new ClientThread(client, textFieldWriter);  // Создаем новый поток, которому передаем клиента
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
