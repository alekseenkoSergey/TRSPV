package pr1;

import java.util.ArrayList;

/**
 * Created by Sergey on 13.03.2017.
 */
class MyThread implements Runnable {
    ArrayList<Integer> array;
    int k;

    public MyThread(ArrayList<Integer> array, int k) {
        this.array = array;
        this.k = k;
    }

    @Override
    public void run() {

    }
}
