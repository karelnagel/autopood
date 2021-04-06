package com.example.autopood.repositorities;

import com.example.autopood.models.Pood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoodRepository extends JpaRepository<Pood, String>
{

}