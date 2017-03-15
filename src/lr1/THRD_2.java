package lr1;

public class THRD_2 implements Runnable {

    private String name = "THRD_2";

    public void run() {
        System.out.println("--------" + name + " is started!");
        Thread thrd_3 = new Thread(new THRD_3());
        try {
            Thread.sleep(1000);
            thrd_3.start();
            thrd_3.join();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("--------" + name + " is finished!");
    }

}
