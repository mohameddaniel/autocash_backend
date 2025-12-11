package com.autocash.entity;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride.GeneratedColumn;
import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
	
	@Column(unique = true,nullable = false,updatable = false)
	private Long ref;
	
	@NotNull
	private Long yearId;
	
	@Column(nullable = true)
	@ColumnDefault("'expertise'")
	private String status = "expertise";
	
	@NotNull
	private Long MonthId;
	
	@Positive(message = "Le prix doit être strictement positif")
	@NotNull(message = "Le prix est obligatoire")
	private Double car_price;
	

	@NotNull(message = "Le kilométrage est obligatoire")
    @PositiveOrZero(message = "Le kilométrage ne peut pas être négatif")
	private Integer mileAge;
	
	private String imageUrl;
	
	@ManyToOne
	@JoinColumn(name = "id_brand")
	private Brand brand;
	
	
	
	@ManyToOne
	@JoinColumn(name = "id_model")
	private Model model;
	
	@NotBlank
	private String city;
	
	
	@ManyToOne
	@JoinColumn(name = "id_seller")
	@JsonBackReference
	private Seller seller;
	
	@CreationTimestamp
	private LocalDateTime createAt;
	
	@PrePersist
	public  void generateRef() {
		if(this.ref == null) {
			this.ref = ThreadLocalRandom.current().nextLong(1000000L, 10000000L);
		}
	}
}
