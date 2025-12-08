package com.autocash.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autocash.apiresponse.ApiResponse;
import com.autocash.entity.Brand;
import com.autocash.services.BrandService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/refs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class ReferenceController {
	private final BrandService brandService;
	
	
	@GetMapping("/brands")
	public ResponseEntity<List<Brand>> getAllBrands() {
		List<Brand> brands =  brandService.getAllBrand();
		return ResponseEntity.ok(brands);
	}
	
	@PostMapping("/brands")
	public ResponseEntity<ApiResponse> addBrand(@RequestBody Brand brand) {
		brandService.addBrand(brand);
		ApiResponse apiRes = new ApiResponse(true, "Brand créé avec succès");
		return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.CREATED);
	}
}
