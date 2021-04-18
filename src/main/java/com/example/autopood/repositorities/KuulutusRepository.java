package com.example.autopood.repositorities;

import com.example.autopood.models.Kuulutus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KuulutusRepository extends JpaRepository<Kuulutus, Long>
{
    //Todo select from parameterDto with all the parameters
    @Query("select p from Kuulutus p where upper(p.brand) like concat('%', upper(?1), '%') and upper(p.model) like concat('%', upper(?2), '%')")
    List<Kuulutus> findByParameter(String brand, String model);
    //    List<Kuulutus> findByParameter(ParameterDto parameter);
}