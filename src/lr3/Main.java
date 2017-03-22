package lr3;

import lr3.Client.Client;
import lr3.Server.Server;

public class Main {

    public static void main(String[] args) {
	// write your code here

        new Server().main(args);

        new Client().main(args);
        new Client().main(args);
        new Client().main(args);


    }
}
