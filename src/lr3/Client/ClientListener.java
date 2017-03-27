package lr3.Client;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Sergey on 27.03.2017.
 */
public class ClientListener extends Thread {
	private ClientWindow clientWindow;

	public ClientListener(ClientWindow clientWindow) {
		this.clientWindow = clientWindow;
	}

	@Override
	public void run() {
		try {
			final ObjectInputStream inputStream = new ObjectInputStream(clientWindow.getSocket().getInputStream());

			while (true) {
				String message = (String) inputStream.readObject();
				clientWindow.printToTextArea(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
