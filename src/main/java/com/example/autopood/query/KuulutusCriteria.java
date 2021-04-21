package com.example.autopood.query;

import com.example.autopood.models.Kuulutus;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class KuulutusCriteria implements Specification<Kuulutus>
{
    public KuulutusCriteria(String key, String operation, Object value)
    {
        this.criteria = new SearchCriteria(key, operation, value);
    }

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Kuulutus> root, CriteriaQuery<?> query, CriteriaBuilder builder)
    {

        if (criteria.getOperation().equalsIgnoreCase(">"))
        {
            var doubleValue = Double.parseDouble(criteria.getValue().toString());
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), doubleValue);
        }
        else if (criteria.getOperation().equalsIgnoreCase("<"))
        {
            var doubleValue = Double.parseDouble(criteria.getValue().toString());
            return builder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), doubleValue);
        }
        else if (criteria.getOperation().equalsIgnoreCase(":"))
        {
            if (root.get(criteria.getKey()).getJavaType() == String.class)
            {
                return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else
            {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}