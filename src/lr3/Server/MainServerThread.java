package lr3.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

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

			// начинаем ждать клиентов в бесконеыном цикле
			while (true) {
				Socket client = null;
				while (client == null) {                    // если возник клиент
					client = socketListener.accept();       // то принимаем соединени
				}
				// Создаем новый поток, которому передаем клиента
				clients.add(new ClientThread(client, this));
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
