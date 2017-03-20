package lr1;

import java.util.concurrent.Callable;

class CallableTask implements Callable<String> {

    private String name;
    private int value;

    public CallableTask(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String call() throws Exception {
        return "I am " + name + " and my value=" + value;
    }

}
