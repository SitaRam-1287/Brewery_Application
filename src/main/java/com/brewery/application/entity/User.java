package com.brewery.application.entity;

import com.brewery.application.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String fireBaseId;

    @Column
    private String email;


    @Column
    private String phoneNum;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Address> addressList;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private String image;


    private LocalDate dateOfBirth;


    @Enumerated(EnumType.STRING)
    private Role role;

}
