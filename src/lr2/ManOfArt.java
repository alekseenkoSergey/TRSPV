package lr2;

/**
 * Created by Sergey on 18.03.2017.
 */
abstract public class ManOfArt {
    Playground playground;
    String name;

    public ManOfArt(Playground playground, String name) {
        this.playground = playground;
        this.name = name;
    }
}
