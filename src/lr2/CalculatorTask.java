package lr2;

import java.util.concurrent.locks.Lock;

/**
 * Created by Sergey on 19.03.2017.
 */
class CalculatorTask implements Runnable {
	int[] array;
	int sector;
	int parts;
	Lock lock;

	public CalculatorTask(int[] array, int sector, int parts, Lock lock) {
		this.array = array;
		this.sector = sector;
		this.parts = parts;
		this.lock = lock;
	}

	@Override
	public void run() {
		long summ = 0;
		for (int i = sector * (array.length / parts); i < (sector + 1) * (array.length / parts); i++) {
			summ += array[i];
		}
		lock.lock();
			ArraySumm.multiThreadSumm += summ;
		try {
		} finally {
			lock.unlock();
		}
	}
}

