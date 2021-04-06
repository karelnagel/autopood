package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;

public class Auto24 extends ScrapePood
{
    public Auto24(PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        super("Auto 24", poodRepository, kuulutusRepository);
        url = "https://www.auto24.ee/kasutatud/nimekiri.php?ae=1&ak=0";
        kuulutuseElement = "div.result-row";
    }

    public String scrapeLink(Element kuulutus)
    {
        var baselink = "https://www.auto24.ee/used";
        var closer = kuulutus.select("a").first();
        var rida = closer.attr("href");
        String link = baselink + rida;
        return link;
    }

    public Kuulutus scrapeKuulutus(String url)
    {
        var kuulutus = new Kuulutus();
        var map = new HashMap<String, String>();
        try
        {
            var document = Jsoup.connect(url).get();
            var elements = document.select("tr");
            for (Element element : elements)
            {
                var name = element.select("td.label").text();
                var value = element.select("span.value").text();
                map.put(name, value);
            }
            var pealkiri = document.select("h1.commonSubtitle").text();
            if (pealkiri.contains(" "))
            {
                String arr[] = pealkiri.split(" ", 2);
                kuulutus.setMark(arr[0]);
                kuulutus.setMudel(arr[1]);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {

            var aastaString = map.get("Esmane reg:");
            if (aastaString.contains("/")) aastaString = aastaString.substring(3);
            var aasta = Integer.parseInt(aastaString.replaceAll("[^\\d.]", ""));
            var hind = Integer.parseInt(map.get("Hind:").replaceAll("[^\\d.]", ""));
            kuulutus.setAasta(aasta);
            kuulutus.setHind(hind);
            kuulutus.setLink(url);
            return kuulutus;
        } catch (NumberFormatException e)
        {
            System.out.println("Cant parse");
            return null;
        } catch (NullPointerException e)
        {
            System.out.println("Element not found");
            return null;
        }

    }

}
