package com.autocash.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.UserDto;
import com.autocash.entity.Seller;
import com.autocash.entity.SellerName;
import com.autocash.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepo;
	private final SellerNameService sellerNameService;
	
	
	@Transactional
	public Long addUser(UserDto dto) {
	    
	    SellerName existingSellerName = sellerNameService.getSellerNameById(dto.getName_id());
	    if (existingSellerName == null) {
	        throw new RuntimeException("SellerName introuvable.");
	    }
	    
	 
	    Seller sellerDetails = new Seller();
	    
	    sellerDetails.setEmail(dto.getEmail());
	    sellerDetails.setCity(dto.getCity());
        sellerDetails.setPhone(dto.getPhone());
        sellerDetails.setAddress(dto.getAddress());
        sellerDetails.setSellerType(dto.getSellerType());
	    sellerDetails.setSellerName(existingSellerName);	
	   
	    Seller updatedSeller = userRepo.save(sellerDetails); 
	    
	    return updatedSeller.getId();
	}
	
	
	@Transactional(readOnly = true)
	public Seller  findUserById(Long id ) {
		Seller user = userRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id ne trouve pas dans la base de données"));
		return user;
	}	
	
	@Transactional(readOnly = true)
	public boolean userExist(Long id) {
		return userRepo.existsById(id);	}
}
