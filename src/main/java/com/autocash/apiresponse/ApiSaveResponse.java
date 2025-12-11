package com.autocash.apiresponse;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ApiSaveResponse {
	
	private final boolean success;
	private final String message;
	private final Long id;
}
