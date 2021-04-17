package com.example.autopood.repositorities;

import com.example.autopood.models.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ParameterRepository extends JpaRepository<Parameter, Long>
{
    List<Parameter> findByUserId(Long userId);
}