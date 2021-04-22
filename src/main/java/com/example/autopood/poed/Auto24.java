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
                kuulutus.setBrand(arr[0]);
                kuulutus.setModel(arr[1]);
            } else
                kuulutus.setBrand(pealkiri);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        try
        {
            var aastaString = map.get("Initial reg:");
            if (aastaString.contains("/")) aastaString = aastaString.substring(3);
            var aasta = Integer.parseInt(aastaString.replaceAll("[^\\d.]", ""));
            var hind = Integer.parseInt(map.get("Price:").replaceAll("[^\\d.]", ""));
            kuulutus.setYear(aasta);
            kuulutus.setPrice(hind);
            kuulutus.setLink(url);

            var carType = map.get("Type:").replaceAll("[^\\d.]", "").strip();
            carType = toUniversal(carType);
            kuulutus.setType(carType);

            var carBodyType = map.get("Bodytype:").replaceAll("[^\\d.]", "").strip();
            kuulutus.setBodyType(StringUtils.capitalize(carBodyType));

            var geartype = map.get("Transmission:").replaceAll("[^\\d.]", "").strip();
            kuulutus.setGearType(geartype);

            var mileage = map.get("Mileage:").strip();
            mileage = mileage
                    .replaceAll(",","")
                    .replaceAll("km","")
                    .replaceAll(" ","");
            kuulutus.setMileage(Integer.parseInt(mileage));

            var fueltype = map.get("Fuel:").strip();
            kuulutus.setFuelType(fueltype);

            var engine = map.get("Engine:").strip();
            var engineL = engine.split(" ")[0];
            var engineKW = engine.split(" ")[1].substring(1).strip();

            kuulutus.setEngineSize(Double.parseDouble(engineL));
            kuulutus.setEngineKW(Double.parseDouble(engineKW));

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
    public static String toUniversal(String s){
        if(s.equals("passenger car")) return "Car";
        else if(s.equals("commercial vehicle")) return "Van";
        else return s;
    }
}
