import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        Timer timer = new Timer();
        var idk = new Refresh();
        idk.run();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        idk.run();
        //ettenatud millisekundite järel teostame uue Refresh()-i
        //timer.schedule(new Refresh(), 0, 2000);
    }
}
