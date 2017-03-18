package lr2;

/**
 * Created by Sergey on 18.03.2017.
 */
public class Playground {

	void exhibitPictures(String artist) throws InterruptedException {
		int delay = 10;
		System.out.println(artist + " заполняет зал картинами.");
		Thread.sleep(delay);
		System.out.println(artist + " проводит выставку...");
		Thread.sleep(delay);
		System.out.println(artist + " завершает выставку.");
	}

    void playPatheticSonate(String musician) throws InterruptedException {
        int delay = 10;
        System.out.println(musician + " начинает исполнение...");
        System.out.println("Звучит часть 1: Allegro");
        Thread.sleep(delay);
        System.out.println("Звучит часть 2: Adagio cantabile");
        Thread.sleep(delay);
        System.out.println("Звучит часть 3: Rondo");
        Thread.sleep(delay);
		System.out.println(musician + " завершил исполнение.");
		System.out.println("----------");
    }

	synchronized void printZimniyVecher(String author) throws InterruptedException {
		int delay = 10;
        System.out.println(author + " начитает писать стих...");
        System.out.println();
        System.out.println("Буря мглою небо кроет");
        Thread.sleep(delay);
        System.out.println("Вихри снежные крутя");
        Thread.sleep(delay);
        System.out.println("То, как зверь, она завоет");
        Thread.sleep(delay);
        System.out.println("То заплачет, как дитя");
        System.out.println();
        System.out.println(author + " завершил написание стиха.");
        System.out.println("----------");
    }
}
