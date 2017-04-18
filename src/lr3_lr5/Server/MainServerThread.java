package lr3_lr5.Server;

import java.util.ArrayList;
import java.util.List;

class MainServerThread extends Thread {

	private TextWriter textFieldWriter;
	private ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	private List<String> history = new ArrayList<String>();

	public MainServerThread(TextWriter textFieldWriter) {
		this.textFieldWriter = textFieldWriter;
	}

	@Override
	public void run() {
		new Thread(new ServerListenerOfRegularClient(this)).start();
		new Thread(new ServerListenerOfRemoteClient(this)).start();
	}

	public void printToTextFieldWriter(String message) {
		textFieldWriter.appendText(message);
	}

	public ArrayList<ClientThread> getClients() {
		return clients;
	}

	synchronized public void addMessageToHistory(String message) {
		this.history.add(message + "\n");
	}

	public List<String> getHistory() {
		return history;
	}

	public void addClientToList(ClientThread clientThread) {
		clients.add(clientThread);
	}
}
