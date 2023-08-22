package com.brewery.application.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;


    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String phoneNum;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addressList;

    @OneToOne
    private Role role;

}
