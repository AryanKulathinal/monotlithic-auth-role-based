package com.example.monolithic_spring_security_product_demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
@Table(name="user_details")
public class AuthUser {
    @Id

    @Column(name = "auth_id")
    private long authId;

    @Column(name = "auth_username")
    private String authUser;

    @Column(name = "auth_password")
    private String authPassword;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "auth_role",joinColumns = @JoinColumn(name = "auth_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Roles> allRoles;


}
