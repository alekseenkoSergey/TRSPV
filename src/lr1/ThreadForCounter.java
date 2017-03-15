package lr1;

import java.util.concurrent.Callable;

public class ThreadForCounter implements Callable<Long> {

    private Counter counter;

    public ThreadForCounter(Counter counter) {
        this.counter = counter;
    }

    public Long call() throws Exception {
        for (int i = 0; i < 1000000; i++) {
            counter.inc();
        }
        return counter.getCounter();
    }

}
