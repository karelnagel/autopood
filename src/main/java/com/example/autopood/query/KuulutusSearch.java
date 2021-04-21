package com.example.autopood.query;

import com.example.autopood.DTOs.ParameterDto;
import com.example.autopood.models.Kuulutus;
import com.example.autopood.repositorities.KuulutusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KuulutusSearch
{
    private KuulutusRepository kuulutusRepository;
    private Specification<Kuulutus> spec;

    public KuulutusSearch(@Autowired KuulutusRepository kuulutusRepository)
    {
        this.kuulutusRepository = kuulutusRepository;
        this.spec = Specification.not(new KuulutusCriteria("id", ":", "0"));
    }

    private boolean isNotNullOrEmpty(String string)
    {
        if (string != null && !string.isEmpty()) return true;
        return false;
    }

    private boolean isNotNull(double arv)
    {
        if (arv != 0) return true;
        return false;
    }

    private void CompareDouble(String attribute, Double min, Double max)
    {
        if (isNotNull(min))
            spec = spec.and(new KuulutusCriteria(attribute, ">", min));
        if (isNotNull(max))
            spec = spec.and(new KuulutusCriteria(attribute, "<", max));
    }

    private void CompareString(String attribute, String value)
    {
        if (isNotNullOrEmpty(value))
            spec = spec.and(new KuulutusCriteria(attribute, ":", value));
    }

    public List<Kuulutus> findKuulutus(ParameterDto para)
    {
        CompareString("brand", para.getBrand());
        CompareString("model", para.getModel());
        CompareString("type", para.getType());
        CompareString("fuelType", para.getFuelType());

        CompareDouble("price", para.getMinPrice(), para.getMaxPrice());
        CompareDouble("year", para.getMinYear().doubleValue(), para.getMaxYear().doubleValue());
        CompareDouble("mileage", para.getMinMileage(), para.getMaxMileage());
        CompareDouble("engineSize", para.getMinEngineSize().doubleValue(), para.getMinEngineSize().doubleValue());
        CompareDouble("engineKW", para.getMinEngineKW().doubleValue(), para.getMaxEngineKW().doubleValue());

        var kuulutused = kuulutusRepository.findAll(spec);
        if (isNotNullOrEmpty(para.getCountry()))
            kuulutused.removeIf(k -> !k.getPood().getCountry().equalsIgnoreCase(para.getCountry())); //Todo find out how to move country to query
        return kuulutused;
    }
}
