package lr2;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 19.03.2017.
 */
class ArraySumm {
	static long oneThreadSumm;
	static long multiThreadSumm;
	static int[] array;

	public static void main(String[] args) throws InterruptedException {
		initializeAndFillArray(1000);
		oneThreadCalculator();
		System.out.println("Без многопоточности:  " + oneThreadSumm);

		ArrayList<Thread> threads = multiThreadCalculator(4);
		for (int i = 0; i < threads.size(); i++) {
			threads.get(i).join();
		}
		System.out.println("С многопоточностью:   " + multiThreadSumm);
	}

	public static void initializeAndFillArray(int n) {
		ArraySumm.array = new int[n];
		Random random = new Random();
		for (int i = 0; i < array.length; i++) {
			array[i] = random.nextInt();
		}
	}

	public static void oneThreadCalculator() {
		for (int i = 0; i < array.length; i++) {
			oneThreadSumm += array[i];
		}
	}

	public static ArrayList<Thread> multiThreadCalculator(int parts) {
		ArrayList<Thread> threads = new ArrayList<Thread>();
		Lock lock = new ReentrantLock();
		for (int sector = 0; sector < parts; sector++) {
			threads.add(new Thread(new CalculatorTask(array, sector, parts, lock)));
			threads.get(sector).start();
		}
		return threads;
	}
}


