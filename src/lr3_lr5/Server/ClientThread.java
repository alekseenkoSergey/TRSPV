package lr3_lr5.Server;

import lr3_lr5.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ClientThread extends Thread {
	private Socket socket;
	private Message message;
	private String login;
	private ObjectOutputStream outputStream;
	private MainServerThread mainServerThread;

	public ClientThread(Socket socket, MainServerThread mainServerThread) {
		this.socket = socket;
		this.mainServerThread = mainServerThread;
		try {
			this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			//добавляет себя в список клиентов
			mainServerThread.addClientToList(this);

			// создаем объект для приёма (десериализации) объектов
			final ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());

			// читаем первое сообщение (что клиент присоединился)
			this.message = (Message) inputStream.readObject();
			this.login = this.message.getLogin();
			mainServerThread.printToTextFieldWriter(login + " connected");

			// выводим все более рание сообщения
			List<String> history = mainServerThread.getHistory();
			for (int i = 0; i < history.size(); i++) {
				this.outputStream.writeObject(history.get(i));
			}

			// дальше начинаем ждать последующих сообщений
			while (true) {
				this.message = (Message) inputStream.readObject();
				String messageString = generateMessageString();
				mainServerThread.printToTextFieldWriter(messageString);
				mainServerThread.addMessageToHistory(messageString);
				broadcastMessageToOtherClients(messageString + "\n");
			}

		} catch (SocketException e) {
			mainServerThread.printToTextFieldWriter(login + " disconnected");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String generateMessageString() {
		return message.getLogin() + " [" + message.getDate() + "]: " + message.getMessage();
	}

	private void broadcastMessageToOtherClients(String messageString) {
		new BroadcastService(mainServerThread.getClients(), this, messageString).start();
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}
}
