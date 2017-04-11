package pr4;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Sergey on 10.04.2017.
 */
class MySpeaker extends Thread {

	Socket socket;
	Double summ;

	public MySpeaker(Socket socket, Double summ) {
		this.socket = socket;
		this.summ = summ;
		this.start();
	}

	@Override
	public void run() {
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject("Алексеенко и Жанько, наша сумма: ");
			outputStream.writeObject((Double) summ);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
