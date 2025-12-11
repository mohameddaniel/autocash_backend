package com.autocash.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.SellerNameDto;
import com.autocash.entity.SellerName;
import com.autocash.repository.SellerNameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerNameService {
	
	private final SellerNameRepository sellerNameRepo;
	
	@Transactional
	public SellerName getSellerNameById(Long id) {
		return sellerNameRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("id de nom vendeur non trouvé"));
	}
	
	@Transactional
	public void saveName(SellerName sellerName) {
		sellerNameRepo.save(sellerName);
	}
	
	@Transactional
	public List<SellerNameDto> getAllNames(){
		return sellerNameRepo.findAll()
				.stream()
				.map(name -> new SellerNameDto(name.getId(), name.getName()))
				.collect(Collectors.toList());
				
	}
}
