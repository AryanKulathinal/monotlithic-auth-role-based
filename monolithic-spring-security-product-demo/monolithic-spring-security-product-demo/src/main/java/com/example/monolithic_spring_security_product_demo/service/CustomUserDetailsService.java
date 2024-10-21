package com.example.monolithic_spring_security_product_demo.service;


import com.example.monolithic_spring_security_product_demo.entity.AuthUser;
import com.example.monolithic_spring_security_product_demo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    AuthUserRepository authUserRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = authUserRepo.findByAuthUser(username);
        return authUser
                .map((userInfo)->new CustomUserDetails(userInfo.getAuthUser(),userInfo.getAuthPassword(), userInfo.getAllRoles()))
                .orElseThrow(()-> new UsernameNotFoundException(username + " not found"));
    }
}

