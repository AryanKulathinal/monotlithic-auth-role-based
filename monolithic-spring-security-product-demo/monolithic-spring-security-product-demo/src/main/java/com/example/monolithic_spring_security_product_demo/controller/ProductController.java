package com.example.monolithic_spring_security_product_demo.controller;

import com.example.monolithic_spring_security_product_demo.entity.Product;
import com.example.monolithic_spring_security_product_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{pid}")
    public Product getProduct(@PathVariable("pid") long productId){
        return productService.getProduct(productId);
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

}
