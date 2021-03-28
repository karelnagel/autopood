import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Vaurioajoneuvo extends Pood {


    Vaurioajoneuvo() {

    }

    @Override
    public void refresh() {
        System.out.println("Vaurioajoneuvo refresh");

        //link on avariiliste autode refreshiks
        //https://www.vaurioajoneuvo.fi/?type=PassengerCar&sale_condition=Vaurioituneena%2C%20korjattavaksi%20liikennek%C3%A4ytt%C3%B6%C3%B6n

        Document fullsource = null;
        Elements listings = null;
        var kuulutustekirjeldused = new ArrayList<String>();
        ArrayList<Kuulutus> kuulutused = new ArrayList<>();


        //var kuulutus

        try {
            fullsource = Jsoup.connect("https://www.vaurioajoneuvo.fi/?condition=no_demo").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            listings = fullsource.select("div[class=col-12 col-lg-3 item-lift-container]");
            for(Element listing : listings){
                String kirjeldus = listing.select("div[class=item-lift-title]").text();
                System.out.println(kirjeldus);
                kuulutustekirjeldused.add(kirjeldus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

/*
        for (int i = 0; i < kuulutustekirjeldused.size(); i++) {
            System.out.println(kuulutustekirjeldused.get(i));
        }
        */

    }

        @Override
        public void andmed () {

        }
    }
