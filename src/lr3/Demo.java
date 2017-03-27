package lr3;

import lr3.Client.Client;
import lr3.Server.Server;


// демонстрация работы
// сразу запускает одного сервера и трёх клментов
public class Demo {

    public static void main(String[] args) {

        new Server().main(args);

        new Client().main(args);
        new Client().main(args);
        new Client().main(args);


    }
}
