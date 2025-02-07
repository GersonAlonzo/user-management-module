package com.sompopo.api.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users") 
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String msisdn; 

    @Column(nullable = false)
    private String password;

    @Embedded 
    private Audit audit = new Audit(); 

}