package com.example.monolithic_spring_security_product_demo.service;

import com.example.monolithic_spring_security_product_demo.entity.Product;
import com.example.monolithic_spring_security_product_demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository prodRepo;

    public List<Product> getAllProducts(){
        return prodRepo.findAll();
    }

    public Product getProduct(Long prodId){
        return prodRepo.findById(prodId).orElseThrow();
    }

    public Product addProduct(Product product){
        return prodRepo.saveAndFlush(product);
    }



}
