package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long qty;
    @Column(nullable = false)
    private Double price;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "vid")
    private Vendor vendor;
    @JsonIgnore
    @OneToOne(mappedBy = "item",cascade = CascadeType.ALL)
    private TiffinDetail tiffinDetail;

    public void assignVendor(Vendor vendor){
        this.vendor = vendor;
    }
    public void assignToTiffinDetail(TiffinDetail tiffinDetail){
        this.tiffinDetail = tiffinDetail;
    }
}
