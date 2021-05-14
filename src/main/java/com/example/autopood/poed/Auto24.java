package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;

public class Auto24 extends ScrapePood
{
    public Auto24(PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        super("Auto 24", "Estonia", poodRepository, kuulutusRepository);
        url = "https://eng.auto24.ee/kasutatud/nimekiri.php?ae=1&ak=0";
        kuulutuseElement = "div.result-row";
    }

    public String scrapeLink(Element kuulutus)
    {
        var baselink = "https://eng.auto24.ee/used";
        var closer = kuulutus.select("a").first();
        var rida = closer.attr("href");
        String link = baselink + rida;
        return link;
    }

    public Kuulutus scrapeKuulutus(String url)
    {
        var kuulutus = new Kuulutus();

        kuulutus.setLink(url);
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
            var pictures = document.select("div.img-container").select("img");
            if (pictures.size() > 0)
                kuulutus.setPicture(pictures.first().attr("src"));


            var pealkiri = document.select("h1.commonSubtitle");
            if (pealkiri.size() == 1)
            {
                if (pealkiri.text().contains(" "))
                {
                    String arr[] = pealkiri.text().split(" ", 2);
                    kuulutus.setBrand(arr[0]);
                    kuulutus.setModel(arr[1]);
                } else
                    kuulutus.setBrand(pealkiri.text());
            }
            if (map.containsKey("Initial reg:"))
            {
                var aasta = map.get("Initial reg:");
                if (aasta.contains("/")) aasta = aasta.substring(3);
                if (!aasta.equals(""))
                    kuulutus.setYear(parseInt(aasta));
            }
            if (map.containsKey("Price:"))
            {
                var price = map.get("Price:");
                if (!price.equals(""))
                    kuulutus.setPrice(parseInt(price));
            }
            if (map.containsKey("Type:"))
            {
                var carType = map.get("Type:").strip();
                kuulutus.setType(toUniversal(carType));
            }

            if (map.containsKey("Bodytype:"))
            {
                var carBodyType = map.get("Bodytype:").strip();
                kuulutus.setBodyType(StringUtils.capitalize(carBodyType));
            }
            if (map.containsKey("Transmission:"))
            {
                var geartype = map.get("Transmission:").strip();
                kuulutus.setGearType(geartype);
            }
            if (map.containsKey("Mileage:"))
            {
                var mileage = map.get("Mileage:");
                if (!mileage.equals(""))
                    kuulutus.setMileage(parseInt(mileage));
            }
            if (map.containsKey("Fuel:"))
            {
                var fueltype = map.get("Fuel:").strip();
                kuulutus.setFuelType(fueltype);
            }

            if (map.containsKey("Engine:"))
            {
                var engine = map.get("Engine:").strip();
                if (engine.contains(" ("))
                {
                    var engineL = engine.substring(0, engine.indexOf("("));
                    var engineKW = engine.substring(engine.indexOf("(") + 1, engine.indexOf("kW"));
                    kuulutus.setEngineSize(parseDouble(engineL));
                    kuulutus.setEngineKW(parseDouble(engineKW));
                }
            }

            return kuulutus;
        } catch (IOException e)
        {
            System.out.println("IOException " + this.poeId);

            e.printStackTrace();
        } catch (NumberFormatException e)
        {
            System.out.println("NumberFormatException " + this.poeId);
        } catch (NullPointerException e)
        {
            System.out.println("NullPointerException " + this.poeId);

        } catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("ArrayIndexOutOfBoundsException " + this.poeId);

        }
        return null;

    }

    private static Double parseDouble(String string)
    {
        try
        {
            return Double.parseDouble(string.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e)
        {
            System.out.println("parse double failed");
            return 0.0;
        }
    }

    private static Integer parseInt(String string)
    {
        try
        {
            return Integer.parseInt(string.replaceAll("[^\\d.]", ""));
        } catch (NumberFormatException e)
        {
            System.out.println("parse int failed ");
            return 0;
        }
    }

    public static String toUniversal(String s)
    {
        if (s.equals("passenger car")) return "Car";
        else if (s.equals("commercial vehicle")) return "Van";
        else return s;
    }
}
