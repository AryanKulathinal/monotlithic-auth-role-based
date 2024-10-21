package com.example.monolithic_spring_security_product_demo.controller;


import com.example.monolithic_spring_security_product_demo.entity.AuthUser;
import com.example.monolithic_spring_security_product_demo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthUserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @PostMapping("/validate")
    public String validate(@RequestBody AuthUser userInfo){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getAuthUser(), userInfo.getAuthPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(userInfo.getAuthUser());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }
    }
}
