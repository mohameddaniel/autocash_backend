package com.autocash.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString(exclude = {"cars", "sellerName"})
public class Seller {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotBlank(message = "Le téléphone est obligatoire")
	private String phone;
	
	@NotBlank(message = "L'email est obligatoire")
    @Email(
        regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
        message = "Format d'email invalide"
    )
	private String email;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String  sellerType;
	
	@NotBlank
	private String city;
	
	@OneToMany(mappedBy = "seller",cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Car> cars = new  ArrayList<Car>();
	
	@ManyToOne 
    @JoinColumn(name = "name_id", nullable = false)
    private SellerName sellerName;
}
