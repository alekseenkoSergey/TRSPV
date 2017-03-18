package lr2;

import java.util.ArrayList;

/**
 * Created by Sergey on 18.03.2017.
 * Играемся с разными вариантами синхронизации потоков
 */
public class Examples {
    public static void main(String[] args) {
        Printer printer = new Printer();

        // на этом примере можно посмотреть как работает synchronized
        pushkinCreativity(printer);
    }

    private static void pushkinCreativity(Printer printer) {
        ArrayList<Thread> pushkings = new ArrayList<Thread>();

        for (int i = 0; i < 3; i++) {
            pushkings.add(new Thread(new Pushkin(printer, "Пушкин" + i)));
        }
        for (int i = 0; i < pushkings.size(); i++) {
            pushkings.get(i).start();
        }
    }
}
