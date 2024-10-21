package com.example.monolithic_spring_security_product_demo.repository;

import com.example.monolithic_spring_security_product_demo.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser,Long> {
    Optional<AuthUser> findByAuthUser(String username);
}
