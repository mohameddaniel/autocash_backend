package com.autocash.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class City {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityid;
	
	@NotBlank
	private String city_name;
}
