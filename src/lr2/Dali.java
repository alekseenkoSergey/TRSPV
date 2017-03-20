package lr2;

import java.util.concurrent.Semaphore;

/**
 * Created by Sergey on 18.03.2017.
 */
class Dali extends ManOfArt implements Runnable {
	Semaphore semaphore;

	public Dali(Playground playground, Semaphore semaphore, String name) {
		super(playground, name);
		this.semaphore = semaphore;
	}

	@Override
	public void run() {
		try {
			semaphore.acquire();
			playground.exhibitPictures(name);
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
