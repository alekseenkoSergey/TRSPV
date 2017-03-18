package lr2;

/**
 * Created by Sergey on 18.03.2017.
 */
public class Pushkin implements Runnable {
    Printer printer;
    String name;

    public Pushkin(Printer printer, String name) {
        this.printer = printer;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            printer.printZimniyVecher(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
