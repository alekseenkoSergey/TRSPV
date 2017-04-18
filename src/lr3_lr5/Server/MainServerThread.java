package lr3_lr5.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServerThread extends Thread {

	private TextWriter textFieldWriter;
	private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	private List<String> history = new ArrayList<String>();

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
				// стартуем
				executor.execute(new ClientThread(client, this));
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
		this.history.add(message + "/n");
	}

	public List<String> getHistory() {
		return history;
	}

	public void addClientToList(ClientThread clientThread) {
		clients.add(clientThread);
	}
}
