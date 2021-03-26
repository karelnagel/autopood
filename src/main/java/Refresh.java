import java.util.ArrayList;
import java.util.TimerTask;

public class Refresh extends TimerTask
{
    public void run()
    {
        var poodideList = new ArrayList<Pood>();

        var mobile = new Mobile();
        var vaurioajoneuvo = new Vaurioajoneuvo();
        poodideList.add(vaurioajoneuvo);
        poodideList.add(mobile);

        //Refresh all shops
        for (Pood pood : poodideList)
        {
            pood.refresh();
        }
    }
}
