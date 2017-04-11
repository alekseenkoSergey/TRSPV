package pr4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

/**
 * Created by Sergey on 10.04.2017.
 */
class MyListener extends Thread {

	Socket socket;

	public MyListener(Socket socket) {
		this.socket = socket;
		this.start();
	}

	@Override
	public void run() {
		try {
			final ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());

			while (true) {
				List<Integer> list = (List<Integer>) inputStream.readObject();
				new Calculator(socket, list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
