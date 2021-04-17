package com.example.autopood.repositorities;

import com.example.autopood.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{

}