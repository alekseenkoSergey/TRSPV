package pr4;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Sergey on 10.04.2017.
 */
class Main extends Thread {

	Socket socket;

	public static void main(String[] args) {
		Main main = new Main();
		main.start();
	}

	@Override
	public void run() {
		try {
			socket = new Socket("192.168.43.184", 1234);
			new MyListener(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
