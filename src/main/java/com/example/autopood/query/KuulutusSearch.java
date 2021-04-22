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

    public KuulutusSearch(@Autowired KuulutusRepository kuulutusRepository)
    {
        this.kuulutusRepository = kuulutusRepository;
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

    private Specification<Kuulutus> CompareDouble(Specification<Kuulutus> spec, String attribute, Double min, Double max)
    {
        if (isNotNull(min))
            spec = spec.and(new KuulutusCriteria(attribute, ">", min));
        if (isNotNull(max))
            spec = spec.and(new KuulutusCriteria(attribute, "<", max));
        return spec;
    }

    private Specification<Kuulutus> CompareString(Specification<Kuulutus> spec, String attribute, String value)
    {
        if (isNotNullOrEmpty(value))
            spec = spec.and(new KuulutusCriteria(attribute, ":", value));
        return spec;
    }

    public List<Kuulutus> findKuulutus(ParameterDto para)
    {
        var spec = Specification.not(new KuulutusCriteria("id", ":", "0"));
        spec = CompareString(spec, "brand", para.getBrand());
        spec = CompareString(spec, "model", para.getModel());
        spec = CompareString(spec, "type", para.getType());
        spec = CompareString(spec, "fuelType", para.getFuelType());

        spec = CompareDouble(spec, "price", para.getMinPrice(), para.getMaxPrice());
        spec = CompareDouble(spec, "year", para.getMinYear().doubleValue(), para.getMaxYear().doubleValue());
        spec = CompareDouble(spec, "mileage", para.getMinMileage(), para.getMaxMileage());
        spec = CompareDouble(spec, "engineSize", para.getMinEngineSize().doubleValue(), para.getMinEngineSize().doubleValue());
        spec = CompareDouble(spec, "engineKW", para.getMinEngineKW().doubleValue(), para.getMaxEngineKW().doubleValue());

        var kuulutused = kuulutusRepository.findAll(spec);
        if (isNotNullOrEmpty(para.getCountry()))
            kuulutused.removeIf(k -> !k.getPood().getCountry().equalsIgnoreCase(para.getCountry())); //Todo find out how to move country to query
        return kuulutused;
    }
}
