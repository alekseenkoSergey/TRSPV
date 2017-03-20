package lr2;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Sergey on 18.03.2017.
 * Играемся с разными вариантами синхронизации потоков
 */
class Examples {
	public static void main(String[] args) {
		Playground playground = new Playground();

         /* несколько Пушкинов одновременно пишут "Зимний вечер"
		 на этом примере можно посмотреть как работает synchronized
         */
//        pushkinCreativity(playground);

        /* несколько Бетховенов одновременно пытаются сыграть Патетическую сонату
        на этом примере можно посмотреть как работает ReentrantLock
         */
//		beethovenCreativity(playground);

        /* несколько Сальвадоров Дали одновременно выставляют свои работы на выставку
		на этом примере можно посмотреть как работает Semaphore
         */
//		daliCreativity(playground);
	}

	private static void daliCreativity(Playground playground) {
		Semaphore semaphore = new Semaphore(3);
		ArrayList<Thread> dalis = new ArrayList<Thread>();
		for (int i = 0; i < 8; i++) {
			dalis.add(new Thread(new Dali(playground, semaphore, "Дали" + i)));
			dalis.get(i).start();
		}
	}

	private static void beethovenCreativity(Playground playground) {
		Lock lock = new ReentrantLock();
		ArrayList<Thread> beethovens = new ArrayList<Thread>();
		int coefPatience = 20;
		for (int i = 0; i < 5; i++) {
			beethovens.add(new Thread(new Beethoven(playground, lock, "Бетховен" + i, i * coefPatience)));
			beethovens.get(i).start();
		}
	}

	private static void pushkinCreativity(Playground playground) {
		ArrayList<Thread> pushkings = new ArrayList<Thread>();
		for (int i = 0; i < 3; i++) {
			pushkings.add(new Thread(new Pushkin(playground, "Пушкин" + i)));
			pushkings.get(i).start();
		}
	}
}
