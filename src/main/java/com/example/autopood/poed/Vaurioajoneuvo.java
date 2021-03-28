package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.poed.Pood;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Vaurioajoneuvo extends Pood
{
    public static String viimanekuulutus = "pole määratud";
    public Vaurioajoneuvo() {
    }

    @Override
    public List<Kuulutus> refresh() {
        System.out.println("------------------------------------\n------ Vaurioajoneuvo refresh ------\n------------------------------------");

        //link on avariiliste autode refreshiks
        //https://www.vaurioajoneuvo.fi/?type=PassengerCar&sale_condition=Vaurioituneena%2C%20korjattavaksi%20liikennek%C3%A4ytt%C3%B6%C3%B6n

        Document fullsource = null;
        Elements listings = null;
        List<String> kuulutustekirjeldused = new ArrayList<String>();
        ArrayList<Integer> millisedEemaldada = new ArrayList<>();
        List<Kuulutus> kuulutused = new ArrayList<>();
        //var kuulutus

        try {
            fullsource = Jsoup.connect("https://www.vaurioajoneuvo.fi/?condition=no_demo").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
            //tõmbame kõik kuulutused elementideks
            listings = fullsource.select("div[class=col-12 col-lg-3 item-lift-container]");
            for(Element listing : listings){
                //peame kontrollima, kas on auto üldse
                String vehicleType = listing.select("p[class=item-lift-dependency]").text();
                if(vehicleType.equals("Myyntiehto: Muu tuote")) {
                    //ei tee nendega midagi, mis on "muu toode"
                    millisedEemaldada.add(listings.indexOf(listing));
                }
                else {
                    String kirjeldus = listing.select("div[class=item-lift-title]").text();
                    kuulutustekirjeldused.add(kirjeldus);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int count = 0;
        for(Integer i : millisedEemaldada){
            listings.remove(listings.get(i-count));
            count = count + 1;
        }
        //System.out.println("Viimane kuulutus oli: "+viimanekuulutus);
        //saime uue viimase ja võrdleme
        String uusviimane = kuulutustekirjeldused.get(0);
        if(isNew(uusviimane)){
            //kui leidsime uue kuulutuse, loome uue kuulutuse ja saadame selle
            var kuulutus = saadakuulutus(listings.get(0));
            kuulutused.add(kuulutus);
        }
        //kui ei saanud uut viimast
        //System.out.println("Uus viimane kuulutus on: "+viimanekuulutus);
return  kuulutused;
    }

    @Override
    public void andmed () {

    }
    public boolean isNew(String viimane) {
        if (viimane.equals(viimanekuulutus)){
            System.out.println("Uusi kuulutusi pole");
        return false;
    }
        else{
            System.out.println("Leidsime uue kuulutuse");
            viimanekuulutus = viimane;
            return true;

        }
    }
    public Kuulutus saadakuulutus(Element auto){
        String mark = scrapeMark(auto);
        String mudel = scrapeMudel(auto);
        int aasta = scrapeAasta(auto);
        int hind = scrapeHind(auto);
        String link = scrapeLink(auto);
        System.out.println("Saadame uue auto:\n Mark: "  + mark + "\n Mudel: " + mudel + "\n Aasta: "+ aasta
                            + "\n Hind: " + hind + "\n Link: " + link);
        return new Kuulutus(mark,mudel,aasta,hind,link);
    }

    public static String scrapeMark(Element kuulutus){
        String rida;
        String mark;
        String[] blok;
        rida = kuulutus.select("div[class=item-lift-title]").text();
        blok = rida.split(" ");
        mark = blok[0];
        return mark;
    }
    public static String scrapeMudel(Element kuulutus){
        String rida;
        String mudel;
        String[] blok;
        rida = kuulutus.select("div[class=item-lift-title]").text();
        blok = rida.split(" ");
        mudel = blok[1];
        return mudel;
    }
    public static int scrapeAasta(Element kuulutus){
        String rida;
        int aasta=1;
        String[] blok;

        rida = kuulutus.select("p[class=item-lift-details]").text();
        blok = rida.split(",");
        rida = blok[1].strip();
        aasta = Integer.parseInt(rida);
        return aasta;
    }
    public static int scrapeHind(Element kuulutus){
        int hind=1;
        String rida;
        String[] blok;
        int pikkus;

        rida = kuulutus.select("div[class=item-lift-price]").text();
        blok = rida.split(" ");
        pikkus = blok.length;
        rida = blok[pikkus-3]+blok[pikkus-2];
        hind = Integer.parseInt(rida);
        return hind;
    }
    public static String scrapeLink(Element kuulutus){
        String rida;
        Element closer;
        String baselink = "https://www.vaurioajoneuvo.fi";

        closer = kuulutus.select("a").first();
        rida = closer.attr("href");
        String link = baselink + rida;
        return link;
    }
}
