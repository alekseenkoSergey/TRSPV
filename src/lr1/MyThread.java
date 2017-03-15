package lr1;

public class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " is started!");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
        }
        System.out.println(name + " is finished!");
    }

}
