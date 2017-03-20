package lr1;

class THRD_1 implements Runnable {

    private String name = "THRD_1";
    private Thread thrd_2;

    public THRD_1(Thread thrd_2) {
        this.thrd_2 = thrd_2;
    }

    public void run() {
        System.out.println("----" + name + " is started!");
        try {
            Thread.sleep(3000);
            thrd_2.join();
        } catch (InterruptedException e) {
        }
        System.out.println("----" + name + " is finished!");
    }

}
