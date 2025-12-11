package com.autoCash.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CarResponseDto {
	private Long id;
	private String SellerName;
	private String model;
	private String brands;
	private Double price;
	private String ville;
	private LocalDateTime createAt;
	private String status;
	private Long ref;
	private String imageUrl;
}
