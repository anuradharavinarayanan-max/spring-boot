package com.example.springboot.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springboot.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}