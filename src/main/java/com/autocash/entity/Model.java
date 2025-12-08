package com.autocash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Model {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long model_id;
	
	@NotBlank
	private String model_name;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
}	
