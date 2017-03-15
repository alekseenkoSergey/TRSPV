package lr1;

import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {
		System.out.println("Main started!");
		System.out.println("--------------------");

		// the first way of create thread
		Thread task = new Thread(new MyTask("task"));
		task.start();

		// the second way of create thread
		MyThread thread = new MyThread("thread");
		thread.start();

        // Main is waiting others
        try {
			System.out.println("Main is waiting the \"task\" and the \"thread\"...");
			task.join();
			thread.join();
			System.out.println("Main is continuing...");
		} catch (InterruptedException e) {}
		System.out.println("--------------------");

        // start thread with the help of executor service
        ExecutorService service1 = Executors.newCachedThreadPool();
		service1.execute(new MyTask("justExecutorServiceTask"));

        // start thread in FixedThreadPool
        ExecutorService service2 = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 5; i++) {
			service2.execute(new MyTask("taskInFixedThreadPool#" + i));
		}

        // callable thread
        Future<String> future = service2.submit(new CallableTask("callableTask", 42));
		try {
			System.out.println(future.get(2, TimeUnit.SECONDS));
		} catch (InterruptedException e) {
		} catch (ExecutionException e) {
		} catch (TimeoutException e) {
		}

        // game with counter
        Counter counter = new Counter();

        ThreadForCounter threadFC1 = new ThreadForCounter(counter);
        ThreadForCounter threadFC2 = new ThreadForCounter(counter);
		ThreadForCounter threadFC3 = new ThreadForCounter(counter);

        ExecutorService service3 = Executors.newFixedThreadPool(2);

        Future<Long> future1 = service3.submit(threadFC1);
        Future<Long> future2 = service3.submit(threadFC2);
		Future<Long> future3 = service3.submit(threadFC3);

        try {
            System.out.println("future1 return " + future1.get());
			System.out.println("future2 return " + future2.get());
			System.out.println("future3 return " + future3.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

        System.out.println("Finally counter have " + counter.getCounter());
        System.out.println("--------------------");

        // scheme of threads
        Thread thrd_2 = new Thread(new THRD_2());
		Thread thrd_1 = new Thread(new THRD_1(thrd_2));

        thrd_1.start();
        try {
            Thread.sleep(2000);
		} catch (InterruptedException e) {}
		thrd_2.start();
		try {
			thrd_1.join();
		} catch (InterruptedException e) {}


        System.out.println("Main finished!");

        service1.shutdown();
        service2.shutdown();
        service3.shutdown();
    }

}
