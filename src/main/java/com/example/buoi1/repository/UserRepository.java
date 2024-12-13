package com.example.buoi1.repository;

import com.example.buoi1.model.UserDemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDemo, Integer> {
    UserDemo findByEmail(String email);
//    <T> ScopedValue<T> findByEmail(String email);
//    <T> ScopedValue<T> findByEmail(String email);
}