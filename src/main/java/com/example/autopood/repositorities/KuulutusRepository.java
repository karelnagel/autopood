package com.example.autopood.repositorities;

import com.example.autopood.models.Kuulutus;
import com.example.autopood.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface KuulutusRepository extends JpaRepository<Kuulutus, Long>, JpaSpecificationExecutor<Kuulutus>
{
    void deleteAllByLink(String link);
}