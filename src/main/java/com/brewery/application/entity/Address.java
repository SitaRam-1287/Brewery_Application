package com.brewery.application.entity;


import com.brewery.application.enums.AddressType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String dNo;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String state;

    @Column
    private String pin;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
