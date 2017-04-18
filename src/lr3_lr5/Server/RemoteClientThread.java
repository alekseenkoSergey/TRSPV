package lr3_lr5.Server;

import lr3_lr5.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by Sergey on 18.04.2017.
 */
class RemoteClientThread implements Runnable {

	private MainServerThread mainServerThread;
	private Socket socket;
	private Message message;
	private String login;
	private ObjectOutputStream outputStream;

	public RemoteClientThread(Socket socket, MainServerThread mainServerThread) {
		this.socket = socket;
		this.mainServerThread = mainServerThread;
		try {
			this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			final ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());
			// читаем первое сообщение (что клиент присоединился)
			this.message = (Message) inputStream.readObject();
			this.login = this.message.getLogin();
			mainServerThread.printToTextFieldWriter(login + " connected");

			// выводим все более рание сообщения
			sendHistory();

			// дальше начинаем ждать последующих сообщений
			while (true) {
				this.message = (Message) inputStream.readObject();
				switch (message.getMessage()) {
					case "GET_HISTORY":
						sendHistory();
						break;
					case "GET_NUM_USERS":
						sendNumUsers();
						break;
					case "GET_CLIENTS_LOGINS":
						sendClientsLogins();
						break;
					default:
						sendErrorUnknownCommand(message.getMessage());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sendErrorUnknownCommand(String command) throws IOException {
		this.outputStream.writeObject("Unknown command " + command + "\n");
	}

	private void sendClientsLogins() throws IOException {
		List<ClientThread> clients = mainServerThread.getClients();
		for (int i = 0; i < clients.size(); i++) {
			this.outputStream.writeObject(clients.get(i).getLogin() + "\n");
		}
	}

	private void sendNumUsers() throws IOException {
		this.outputStream.writeObject("Number of users: " + mainServerThread.getClients().size() + "\n");
	}

	private void sendHistory() throws IOException {
		List<String> history = mainServerThread.getHistory();
		for (int i = 0; i < history.size(); i++) {
			this.outputStream.writeObject(history.get(i));
		}
	}
}
