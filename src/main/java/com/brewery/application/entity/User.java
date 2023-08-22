package com.brewery.application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private  String firstName;

    @Column
    private String lastName;


    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNum;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_id",referencedColumnName ="id")
    private Role role;

}
