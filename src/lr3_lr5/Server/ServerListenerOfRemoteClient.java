package lr3_lr5.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Sergey on 18.04.2017.
 */
class ServerListenerOfRemoteClient implements Runnable {

	MainServerThread mainServerThread;

	public ServerListenerOfRemoteClient(MainServerThread mainServerThread) {
		this.mainServerThread = mainServerThread;
	}

	@Override
	public void run() {
		try {
			//Создаем слушателя
			ServerSocket socketListener = new ServerSocket(3128);
			ExecutorService executor = Executors.newFixedThreadPool(5);
			// начинаем ждать клиентов в бесконеыном цикле
			while (true) {
				Socket client = null;
				while (client == null) {                    // если возник клиент
					client = socketListener.accept();       // то принимаем соединени
				}
				// стартуем
				executor.execute(new RemoteClientThread(client, mainServerThread));
			}
		} catch (SocketException e) {
			System.err.println("Socket exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("I/O exception");
			e.printStackTrace();
		}
	}
}
