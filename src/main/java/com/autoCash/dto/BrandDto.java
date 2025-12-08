package com.autoCash.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BrandDto {
	private Long brand_id;
	private String brand_name;
}
