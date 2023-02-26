package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    @Column
    private Boolean isDelivered;
    @OneToOne(mappedBy = "order")
    private Customer customer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "dpid")
    private DeliveryPersonnel deliveryPersonnel;

   public void assignCustomer(Customer customer){
       this.customer = customer;
   }
   public void assignDeliveryPersonnel(DeliveryPersonnel deliveryPersonnel){
       this.deliveryPersonnel = deliveryPersonnel;
   }
}
