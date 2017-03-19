package lr2;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 19.03.2017.
 */
public class ArraySum {
	static long summWithOutMultithread = 0;
	static long summWithMultithread = 0;

	public static void main(String[] args) throws InterruptedException {
		int n = 1000;
		int[] array = new int[n];
		int parts = 4;
		Random random = new Random();
		Lock lock = new ReentrantLock();

		// Инициализируем массив, считаем сумму элементов без многопоточноти
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt();
			summWithOutMultithread += array[i];
		}
		System.out.println("Без многопоточности:  " + summWithOutMultithread);

		// Создаем и запускаем потоки
		for (int sector = 0; sector < parts; sector++) {
			new Thread(new ArrayCalc(array, sector, parts, lock)).start();
		}

		Thread.sleep(3000);
		System.out.println("С многопоточностью:   " + summWithMultithread);
	}
}

class ArrayCalc implements Runnable {
	int[] array;
	int sector;
	int parts;
	Lock lock;

	public ArrayCalc(int[] array, int sector, int parts, Lock lock) {
		this.array = array;
		this.sector = sector;
		this.parts = parts;
		this.lock = lock;
	}

	@Override
	public void run() {
		lock.lock();
		try {
			for (int i = sector * (array.length / parts); i < (sector + 1) * (array.length / parts); i++) {
				ArraySum.summWithMultithread += array[i];
			}
		} finally {
			lock.unlock();
		}
	}
}
