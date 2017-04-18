package lr3_lr5.Client;

import lr3_lr5.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Sergey on 18.04.2017.
 */
class RegularClient extends AbstractClientWindow {

	public RegularClient() {
		connect();
		new ClientListener(this).start();
	}

	private boolean connect() {

		try {
			// содключаем сокет
			socket = new Socket("localhost", 1234);

			// создает объект для отправки сообщений
			outputStream = new ObjectOutputStream(this.socket.getOutputStream());

			// установить соединение, отправить серверу сообщение о подключении
			outputStream.writeObject(new Message(login, ""));

		} catch (IOException e) {
			textArea.append("Couldn't connect to server!\n");
			return false;
		}

		return true;
	}
}
