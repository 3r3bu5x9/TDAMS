package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name ="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    @Column(nullable = false)
    private Double balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uid")
    private UserC userCc;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tid")
    private Tiffin tiffin;
    @JsonIgnore
    @OneToOne
    @JoinColumn
    private Order order;
    public void toUser(UserC userC){
        this.userCc = userC;
    }
    public void assignTiffin(Tiffin tiffin){
        this.tiffin = tiffin;
    }
    public void assignOrder(Order order){
        this.order = order;
    }
}
