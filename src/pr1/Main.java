package pr1;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sergey on 13.03.2017.
 */
public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        int k = 87;

        for (int i = 0; i < 1024; i++) {
            Random random = new Random();
            array.add(random.nextInt());
        }

        int numberOfThreads = 2;

        ArrayList<MyThread> threads = new ArrayList<MyThread>();
        for (int i = 0; i < numberOfThreads; i++) {

        }

    }

}
