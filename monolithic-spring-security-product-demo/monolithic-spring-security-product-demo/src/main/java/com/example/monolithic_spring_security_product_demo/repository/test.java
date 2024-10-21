package com.example.monolithic_spring_security_product_demo.repository;
import java.util.*;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class test {
    public static void main(String[] args) {
        System.out.print("Enter password to be encrypted : ");
        Scanner sc= new Scanner(System.in);
        String plain_pass= sc.nextLine();
        String pw_hash = BCrypt.hashpw(plain_pass, BCrypt.gensalt());
        System.out.println("The encrypted password is : "+ pw_hash);
}
}
