package com.autocash.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride.GeneratedColumn;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Car {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long car_id;
	
	@Positive(message = "Le prix doit être strictement positif")
	@NotNull(message = "Le prix est obligatoire")
	private Double car_price;
	
	@NotNull(message = "L'année est obligatoire")
    @Min(value = 2025, message = "L'année ne peut pas être dans le futur")
	private Integer car_year;
	
	@NotNull
	@Min(1)
	@Max(12)
	private Integer car_Month;
	

	@NotNull(message = "Le kilométrage est obligatoire")
    @PositiveOrZero(message = "Le kilométrage ne peut pas être négatif")
	private String mileAge;
	
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "id_brand")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "id_model")
	private Model model;
	
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;
	
	@ManyToOne
	@JoinColumn(name = "id_seller")
	private User seller;
	
	@CreationTimestamp
	private LocalDateTime createAt;
}
