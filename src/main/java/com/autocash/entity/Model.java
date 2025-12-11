package com.autocash.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Entity
@Data
public class Model {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long model_id;

    @NotBlank
    private String model_name;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Year> years = new ArrayList<>();

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Month> months = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "brand_id")
    @JsonBackReference
    private Brand brand;
}
