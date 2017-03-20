package lr2;

/**
 * Created by Sergey on 18.03.2017.
 */
class Pushkin extends ManOfArt implements Runnable {
    public Pushkin(Playground playground, String name) {
        super(playground, name);
    }

    @Override
    public void run() {
        try {
            playground.printZimniyVecher(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
