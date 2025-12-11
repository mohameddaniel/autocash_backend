package com.autocash.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.ModelDto;
import com.autoCash.dto.ModelResponseDto;
import com.autocash.entity.Brand;
import com.autocash.entity.Model;
import com.autocash.repository.ModelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelService {
     
    private final ModelRepository modelRepo;
    private final BrandService brandService;
    
    
    @Transactional(readOnly = true)
    public List<ModelResponseDto> getAllModelByBrandId(Long Id) {
    	  return modelRepo.getAllModelByBrand(Id)
    			  .stream()
    			  .map(model -> {
    				  ModelResponseDto dto = new ModelResponseDto();
    				  dto.setModel_name(model.getModel_name());
    				  dto.setModel_id(model.getModel_id());
    				  
    				  return dto;
    			  })
    			  .collect(Collectors.toList());
    }
    
    
    @Transactional
    public Long addModel( ModelDto dto) {
    	Brand b = brandService.getBrandById(dto.getBrand_id());
    	
    	Model model = new Model();
    	model.setModel_name(dto.getModel_name());
    	model.setBrand(b);
    	Model newM = modelRepo.save(model);
    	
    	return newM.getModel_id();
    }
    
    	
    @Transactional(readOnly = true)
    public boolean modelExist(Long id) {
    	return modelRepo.existsById(id);
    }
    
    @Transactional(readOnly = true)
    public Model getModelById(Long id) {
    	Model model = modelRepo.findById(id)
    				  .orElseThrow(() -> new RuntimeException("Id model intouvable"));
    	return model;
    }
	
	
}
