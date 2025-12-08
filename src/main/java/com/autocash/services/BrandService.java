package com.autocash.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.BrandDto;
import com.autocash.entity.Brand;
import com.autocash.repository.BrandRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j

public class BrandService {
	
	private final BrandRepository brandRepo;

	
	
	@Transactional(readOnly = true)
	public Brand getBrandById(Long id) {
		log.debug("Attempting to fetch Brand with id: {}",id);
		
		return brandRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Marque introuvable avec l'id : \" + id"));
	}
	
	
	@Transactional(readOnly = true)
	public List<Brand>  getAllBrand(){
		
		return brandRepo.findAll();
	}
	
	
	@Transactional
	public void addBrand(Brand brand) {
		brandRepo.save(brand);
	}
}
