package lr3.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServerThread extends Thread {

	private TextWriter textFieldWriter;
	private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	private String history = "";

	public MainServerThread(TextWriter textFieldWriter) {
		this.textFieldWriter = textFieldWriter;
	}

	@Override
	public void run() {
		try {
			//Создаем слушателя
			ServerSocket socketListener = new ServerSocket(1234);

			ExecutorService executor = Executors.newFixedThreadPool(5);

			// начинаем ждать клиентов в бесконеыном цикле
			while (true) {
				Socket client = null;
				while (client == null) {                    // если возник клиент
					client = socketListener.accept();       // то принимаем соединени
				}
				// Создаем новый поток, которому передаем клиента
				ClientThread clientThread = new ClientThread(client, this);
				// TODO добавить себя в коллекцию
				executor.execute(clientThread);
				clients.add(clientThread);
			}
		} catch (SocketException e) {
			System.err.println("Socket exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O exception");
			e.printStackTrace();
		}
	}

	public void printToTextFieldWriter(String message) {
		textFieldWriter.appendText(message);
	}

	public ArrayList<ClientThread> getClients() {
		return clients;
	}

	synchronized public void addMessageToHistory(String message) {
		this.history += message;
	}

	public String getHistory() {
		return history;
	}
}
