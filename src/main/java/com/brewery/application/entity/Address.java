package com.brewery.application.entity;


import com.brewery.application.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @Id
    private UUID id ;

    private String dNo;


    private String street;

    private String city;

    private String state;

    private String pincode;

    private AddressType addressType;

    @ManyToOne
    @JoinColumn(name="user",referencedColumnName = "id")
    private User user;


}
