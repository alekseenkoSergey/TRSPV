package lr3_lr5;

import lr3_lr5.Client.Client;
import lr3_lr5.Server.Server;


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
