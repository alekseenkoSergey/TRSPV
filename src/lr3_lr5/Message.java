package lr3_lr5;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

// сериализуемый объект для сообщений
public class Message implements Serializable {

    private String login;
    private String message;
    private Date time;

    public Message(String login, String message){
        this.login = login;
        this.message = message;
        this.time = java.util.Calendar.getInstance().getTime();
    }

    public String getLogin() {
        return this.login;
    }

    public String getMessage() {
        return this.message;
    }

    public String getDate(){
        Time tm = new Time(this.time.getTime());
        return tm.toString();
    }
}