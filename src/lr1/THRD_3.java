package lr1;

public class THRD_3 implements Runnable {

    private String name = "THRD_3";

    public void run() {
        System.out.println("------------" + name + " is started!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println("------------" + name + " is finished!");
    }

}
