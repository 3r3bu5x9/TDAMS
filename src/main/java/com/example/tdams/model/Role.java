package com.example.tdams.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    @Column(nullable = false)
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "role")
    private List<UserC> userCr = new ArrayList<>();
    public void assignUser(UserC userC){
        this.userCr.add(userC);
    }
}
