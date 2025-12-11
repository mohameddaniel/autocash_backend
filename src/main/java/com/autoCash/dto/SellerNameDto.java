package com.autoCash.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SellerNameDto {
	private final Long id;
	private final String name;
}
