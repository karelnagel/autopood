package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.HashMap;

public class Vaurioajoneuvo extends ScrapePood
{
    public Vaurioajoneuvo(PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        super("Vaurioajoneuvo","Finland" ,poodRepository,kuulutusRepository);
        url = "https://www.vaurioajoneuvo.fi/?condition=no_demo";
        kuulutuseElement = "div[class=col-12 col-lg-3 item-lift-container]";
    }

    public String scrapeLink(Element kuulutus)
    {
        var baselink = "https://www.vaurioajoneuvo.fi";

        var closer = kuulutus.select("a").first();
        var rida = closer.attr("href");
        String link = baselink + rida;
        return link;
    }

    public Kuulutus scrapeKuulutus(String url) {
        var kuulutus = new Kuulutus();
        var map = new HashMap<String, String>();
        try {
            var document = Jsoup.connect(url).get();
            var elements = document.select("li.detail");
            for (Element element : elements) {
                var name = element.select("span.name").text();
                var value = element.select("span.value").text();
                map.put(name, value);
            }
            var hind = Integer.parseInt(document.select("p.price").get(0).text().replaceAll("[^\\d.]", ""));
            kuulutus.setPrice(hind);
        } catch (NumberFormatException e)
        {
            System.out.println("Cant parse");
            return null;
        } catch (NullPointerException e)
        {
            System.out.println("Element not found");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        kuulutus.setBrand(map.get("Merkki"));
        kuulutus.setModel(map.get("Malli"));
        kuulutus.setYear(Integer.parseInt(map.get("Käyttöönottovuosi")));
        kuulutus.setLink(url);
        return kuulutus;

    }
}
