package lr2;

/**
 * Created by Sergey on 18.03.2017.
 */
public class Printer {

    synchronized void printZimniyVecher(String author) throws InterruptedException {
        int delay = 10;
        System.out.println(author + " начитает писать стих..");
        System.out.println();
        Thread.sleep(delay);
        System.out.println("Буря мглою небо кроет");
        Thread.sleep(delay);
        System.out.println("Вихри снежные крутя");
        Thread.sleep(delay);
        System.out.println("То, как зверь, она завоет");
        Thread.sleep(delay);
        System.out.println("То заплачет, как дитя");
        Thread.sleep(delay);
        System.out.println();
        System.out.println(author + " завершил написание стиха.");
        System.out.println("----------");
    }
}
