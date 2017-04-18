package lr3_lr5.Client;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Created by Sergey on 27.03.2017.
 */
class ClientListener extends Thread {
	private AbstractClientWindow abstractClientWindow;

	public ClientListener(AbstractClientWindow abstractClientWindow) {
		this.abstractClientWindow = abstractClientWindow;
	}

	@Override
	public void run() {
		try {
			final ObjectInputStream inputStream = new ObjectInputStream(abstractClientWindow.getSocket().getInputStream());

			while (true) {
				String message = (String) inputStream.readObject();
				abstractClientWindow.printToTextArea(message);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
