package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tiffindetail")
public class TiffinDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tdid;
    @Column
    private Long qty;
    @OneToOne
    @JoinColumn(name = "iid")
    private Item item;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "tid")
    private Tiffin tiffin;

    public void assignItem(Item item){
        this.item = item;
    }
    public void assignTiffin(Tiffin tiffin){
        this.tiffin = tiffin;
    }
}
