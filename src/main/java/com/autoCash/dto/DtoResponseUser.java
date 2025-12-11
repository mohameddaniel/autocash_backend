package com.autoCash.dto;


import lombok.Data ;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor

public class DtoResponseUser {
	private final Long id;
	private final String name;
	private final String sellerType;
	private final String email;
	private final String phone;
	private final String city;
	private final String address;	
}
