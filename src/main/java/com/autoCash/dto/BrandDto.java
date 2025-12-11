package com.autoCash.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor

public class BrandDto {
	private final Long brand_id;
	private final String brand_name;
}
