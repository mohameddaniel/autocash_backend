package com.autocash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Brand {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brand_id;
	
	@NotBlank(message = "La marque est obligatoire")
	private String brand_name  ;
}
