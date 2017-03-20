package lr2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * Created by Sergey on 18.03.2017.
 */
class Beethoven extends ManOfArt implements Runnable {
    Lock lock;
    int patience;

    public Beethoven(Playground playground, Lock lock, String name, int patience) {
        super(playground, name);
        this.lock = lock;
        this.patience = patience;
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(patience, TimeUnit.MILLISECONDS)) {
                try {
                    playground.playPatheticSonate(name);
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println(name + " не потерпел ждать, пока какой-то проходимец освободит зал!");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}