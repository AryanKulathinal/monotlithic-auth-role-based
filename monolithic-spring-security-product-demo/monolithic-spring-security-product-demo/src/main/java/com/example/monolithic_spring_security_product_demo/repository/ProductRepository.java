package com.example.monolithic_spring_security_product_demo.repository;

import com.example.monolithic_spring_security_product_demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
