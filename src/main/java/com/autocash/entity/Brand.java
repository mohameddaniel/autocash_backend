package com.autocash.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Brand {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long brand_id;
	
	@NotBlank(message = "La marque est obligatoire")
	private String brand_name  ;
	
	
	
	@OneToMany(mappedBy = "brand",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonManagedReference
	private List<Model> model = new ArrayList<Model>();
}
