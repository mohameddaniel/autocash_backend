package com.autocash.services;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.autoCash.dto.CarFormData;
import com.autoCash.dto.CarResponseDto;
import com.autocash.entity.Brand;
import com.autocash.entity.Car;
import com.autocash.entity.Model;
import com.autocash.entity.Seller;
import com.autocash.repository.CarRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {
	
	private final CarRepository carRepo ;
	private final UserService userService;
	private final BrandService brandService;
	private final ModelService modelService;
	
	private String UPLOAD_DIR = "src/main/resources/static/upload/cars/";
	
	
	@Transactional
	public Long addCar(CarFormData dto)  throws Exception{
		
		Car car =  new Car();
		
		car.setCar_price(dto.getCar_price());
		car.setMileAge(dto.getMile_age());
		car.setCity(dto.getCity());
		car.setYearId(dto.getYearId());
        car.setMonthId(dto.getMonthId());
		
		Seller user = userService.findUserById(dto.getId_seller());
		if (user == null) {
		    throw new IllegalArgumentException("Vendeur introuvable");
		}

		Brand brand = brandService.getBrandById(dto.getId_brand());
		if (brand == null) {
		    throw new IllegalArgumentException("Brand introuvable");
		}

		Model model = modelService.getModelById(dto.getId_model());
		if (model == null) {
		    throw new IllegalArgumentException("Modèle introuvable");
		}

		
		car.setSeller(user);
		car.setBrand(brand);
		car.setModel(model);
		
		
		Car c = carRepo.save(car);
		return c.getCar_id();
	}
	
	
	
	@Transactional
	public void  saveImage(Long id ,MultipartFile file) throws Exception{
		
		File diroctory = new File(UPLOAD_DIR);
		if(!diroctory.exists()) {
			diroctory.mkdirs();	
		}
		
		String uniqueName = UUID.randomUUID().toString() +"_" + file.getOriginalFilename();
		Path filePath = Paths.get(UPLOAD_DIR + uniqueName);
	
		Files.copy(file.getInputStream(), filePath,StandardCopyOption.REPLACE_EXISTING);
		System.out.print(filePath);
		Car car = carRepo.findById(id).orElseThrow(() -> new RuntimeException("id de voiture est  introuvable"));
		String db_url = "/upload/cars/" + uniqueName;
		car.setImageUrl(db_url);
		carRepo.save(car);  
		
	}
	
	@Transactional(readOnly = true)
	public List<CarResponseDto> getAllCars(){
		return carRepo.findAll()
				.stream()
				.map(c -> {
					CarResponseDto cf = new CarResponseDto();
					cf.setBrands(c.getBrand().getBrand_name());
					cf.setId(c.getCar_id());
					cf.setImageUrl(c.getImageUrl());
					cf.setModel(c.getModel().getModel_name());
					cf.setSellerName(c.getSeller().getSellerName().getName());
					cf.setVille(c.getCity());
					cf.setCreateAt(c.getCreateAt());
					cf.setPrice(c.getCar_price());
					cf.setRef(c.getRef());
					cf.setStatus(c.getStatus());
					
					
					return cf;
				})
				.collect(Collectors.toList());
	}
	
	
}
