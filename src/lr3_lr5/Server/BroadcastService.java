package lr3_lr5.Server;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sergey on 28.03.2017.
 */
public class BroadcastService extends Thread {
	private ArrayList<ClientThread> clients;
	private String message;
	private ClientThread sourse;

	public BroadcastService(ArrayList<ClientThread> clients, ClientThread sourse, String message) {
		this.clients = clients;
		this.message = message;
		this.sourse = sourse;
	}

	@Override
	public void run() {
		for (ClientThread client : clients) {
			if (client != sourse) {
				sendMessageToClient(client, message);
			}
		}
	}

	private void sendMessageToClient(ClientThread client, String messageString) {
		try {
			client.getOutputStream().writeObject(messageString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
