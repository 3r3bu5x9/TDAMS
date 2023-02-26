package com.example.tdams.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "tiffin_tb")
public class Tiffin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tid;
    @OneToMany(mappedBy = "tiffin",cascade = CascadeType.ALL)
    private List<TiffinDetail> tiffinDetails = new ArrayList<>();
    @OneToOne(mappedBy = "tiffin")
    private Customer customer;

    public void addDetails(TiffinDetail tiffinDetail){
        this.tiffinDetails.add(tiffinDetail);
    }
    public void assignCustomer(Customer customer){
        this.customer = customer;
    }
}
