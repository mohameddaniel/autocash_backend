package com.autocash.apiresponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ApiResponse {
	private final boolean success;
	private  final String message;
}
