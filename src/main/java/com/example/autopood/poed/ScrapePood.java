package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ScrapePood extends AbstractPood
{
    String url;
    String kuulutuseElement;

    ScrapePood(String poeNimi, PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        super(poeNimi, poodRepository, kuulutusRepository);
    }

    public List<Kuulutus> getKuulutused()
    {
        var kuulutused = new ArrayList<Kuulutus>();
        var urls = scrapeKuulutusteLingid();
        for (String url : urls)
        {
            kuulutused.add(scrapeKuulutus(url));
        }
        return kuulutused;
    }

    public List<String> scrapeKuulutusteLingid()
    {
        try
        {
            List<String> returnValue = new ArrayList<>();
            var document = Jsoup.connect(url).get();
            var elements = document.select(kuulutuseElement);
            for (Element element : elements)
            {
                var url = scrapeLink(element);
                if (url.equals(getViimaneKuulutus()) || elements.indexOf(element) == 1 && getViimaneKuulutus() == null)
                    break;
                returnValue.add(url);
            }
            return returnValue;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Kuulutus scrapeKuulutus(String url);

    public abstract String scrapeLink(Element kuulutus);
}
