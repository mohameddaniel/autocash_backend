package com.autocash.entity;

import com.autocash.enums.SellerType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String sellerName;
	
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
	
	@Enumerated(EnumType.STRING)
	private SellerType sellerType;
	
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
}
