package com.example.autopood.poed;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.Pood;
import com.example.autopood.repositorities.KuulutusRepository;
import com.example.autopood.repositorities.PoodRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractPood
{
    @Autowired
    public KuulutusRepository kuulutusRepository;
    public PoodRepository poodRepository;
    public String poeId;

    AbstractPood(String poeNimi, PoodRepository poodRepository, KuulutusRepository kuulutusRepository)
    {
        if (!poodRepository.existsById(poeNimi))
        {
            var pood = new Pood();
            pood.setId(poeNimi);
            poodRepository.save(pood);
        }
        this.poeId = poeNimi;
        this.poodRepository = poodRepository;
        this.kuulutusRepository = kuulutusRepository;
    }

    public abstract List<Kuulutus> getKuulutused();

    public List<Kuulutus> refresh()
    {
        var kuulutused = getKuulutused();

        var pood = getPoodFromDB();
        kuulutused.removeIf(k -> k == null);
        for (Kuulutus kuulutus : kuulutused)
        {
            kuulutus.setPood(pood);
        }
        kuulutusRepository.saveAll(kuulutused);
        if (kuulutused.size() > 0)
            setViimaneKuulutus(kuulutused.get(0).getLink());
        return kuulutused;
    }

    public String getViimaneKuulutus()
    {
        if (!poodRepository.existsById(poeId)) return null;
        var pood = poodRepository.findById(poeId).get();
        return pood.getViimaneKuulutus();
    }

    public void setViimaneKuulutus(String viimaneKuulutus)
    {
        var pood = getPoodFromDB();
        pood.setViimaneKuulutus(viimaneKuulutus);
        poodRepository.save(pood);
    }

    public Pood getPoodFromDB()
    {
        if (!poodRepository.existsById(poeId)) return null;
        return poodRepository.findById(poeId).get();
    }
}
