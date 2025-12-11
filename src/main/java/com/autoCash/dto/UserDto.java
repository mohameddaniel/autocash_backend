package com.autoCash.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data

@RequiredArgsConstructor
public class UserDto {
	private final Long id;
	private final Long name_id;
	private final String sellerType;
	private final String email;
	private final String phone;
	private final String city;
	private final String address;	
}
