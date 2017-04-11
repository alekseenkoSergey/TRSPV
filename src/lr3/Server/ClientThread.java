package lr3.Server;

import lr3.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
	private Socket socket;
	private Message c;
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
			// создаем объект для приёма (десериализации) объектов
			final ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());

			// читаем первое сообщение (что клиент присоединился)
			this.c = (Message) inputStream.readObject();
			this.login = this.c.getLogin();
			mainServerThread.printToTextFieldWriter(login + " connected");

			// выводим все более рание сообщения
			this.outputStream.writeObject(mainServerThread.getHistory());

			// дальше начинаем ждать последующих сообщений
			while (true) {
				this.c = (Message) inputStream.readObject();
				String messageString = generateMessageString();
				mainServerThread.printToTextFieldWriter(messageString);
				mainServerThread.addMessageToHistory(messageString + "\n");
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
		return c.getLogin() + " [" + c.getDate() + "]: " + c.getMessage();
	}

	private void broadcastMessageToOtherClients(String messageString) {
		new BroadcastService(mainServerThread.getClients(), this, messageString).start();
	}

	public ObjectOutputStream getOutputStream() {
		return outputStream;
	}
}
