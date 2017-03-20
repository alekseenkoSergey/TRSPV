package lr1;

class MyTask implements Runnable {

    private String name;

    public MyTask(String name) {
        this.name = name;
    }

    public void run() {
        System.out.println(name + " is started!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        System.out.println(name + " is finished!");
    }

}
