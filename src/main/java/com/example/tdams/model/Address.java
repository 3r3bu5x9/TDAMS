package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    @Column(nullable = false)
    private String flatNo;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String landmark;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String pincode;
    @JsonIgnore
    @OneToOne(mappedBy = "address")
    private UserC userCa;
    public void assignUser(UserC userC){
        this.userCa = userC;
    }
}
