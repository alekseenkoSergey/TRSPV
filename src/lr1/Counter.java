package lr1;

public class Counter {
    private long counter = 0;

    // достаточно объявить этот метод synchronized, и проблема гонки потоков будет решена
    public void inc() {
        counter++;
    }

    public long getCounter() {
        return counter;
    }
}
