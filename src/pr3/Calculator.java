package pr3;

import java.net.Socket;
import java.util.List;

/**
 * Created by Sergey on 10.04.2017.
 */
class Calculator extends Thread {

	Socket socket;
	List<Integer> list;
	Double summ = 0.0;

	public Calculator(Socket socket, List<Integer> list) {
		this.socket = socket;
		this.list = list;
		this.start();
	}

	@Override
	public void run() {
		for (int i = 0; i < list.size(); i++) {
			summ += list.get(i);
		}
		new MySpeaker(socket, summ);
	}
}
