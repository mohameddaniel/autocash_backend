package com.autocash.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autoCash.dto.BrandDto;
import com.autoCash.dto.CarFormData;
import com.autoCash.dto.CarResponseDto;
import com.autoCash.dto.DaoMonth;
import com.autoCash.dto.DaoYear;
import com.autoCash.dto.DtoResponseUser;
import com.autoCash.dto.ModelDto;
import com.autoCash.dto.ModelResponseDto;
import com.autoCash.dto.SellerNameDto;
import com.autoCash.dto.UserDto;
import com.autocash.apiresponse.ApiResponse;
import com.autocash.apiresponse.ApiSaveResponse;
import com.autocash.entity.Brand;
import com.autocash.entity.Model;
import com.autocash.entity.Month;
import com.autocash.entity.Seller;
import com.autocash.entity.SellerName;
import com.autocash.entity.Year;
import com.autocash.services.BrandService;
import com.autocash.services.CarService;
import com.autocash.services.ModelService;
import com.autocash.services.MonthService;
import com.autocash.services.SellerNameService;
import com.autocash.services.UserService;
import com.autocash.services.YearService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/refs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class ReferenceController {
	private final BrandService brandService;
	private final ModelService modelService;
	private final UserService userService;
	private final CarService carService;
	private final SellerNameService sellerNameService;
	private final YearService yearService;
	private final MonthService monthService;
	
	
	
	// barnds apis
	@GetMapping("/brands")
	public ResponseEntity<List<BrandDto>> getAllBrands() {
		List<BrandDto> brands =  brandService.getAllBrand();
		return ResponseEntity.ok(brands);	
	}
	
	@PostMapping("/brands")
	public ResponseEntity<ApiResponse> addBrand(@ Valid @RequestBody Brand brand) {
		brandService.addBrand(brand);
		ApiResponse apiRes = new ApiResponse(true, "Brand créé avec succès");
		return new ResponseEntity<ApiResponse>(apiRes,HttpStatus.CREATED);
	}
	
	
	//models apis
	
	@PostMapping("/models")
	public ResponseEntity<ApiSaveResponse> addModel(@Valid @RequestBody ModelDto dto) {
		Long id = modelService.addModel(dto);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(new ApiSaveResponse(true, "Modèle ajouté avec succès",id));
	}

	
	
	@GetMapping("/models")
	public ResponseEntity<List<ModelResponseDto>> getAllModelByBrandId(@RequestParam("id") Long id) {
		List<ModelResponseDto> model = modelService.getAllModelByBrandId(id);
		return new ResponseEntity<List<ModelResponseDto>>(model,HttpStatus.ACCEPTED);
	}
	
	
	//Api yaer 
	
	@PostMapping("years")
	public ResponseEntity<ApiResponse> addYear(@Valid @RequestBody DaoYear dto) {
		yearService.saveYear(dto);
		
		ApiResponse api  = new ApiResponse(true, "année de mise en circulation  est ajoutée ");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(api);
	}
	
	@GetMapping("years")
	public ResponseEntity<List<Year>> getAllYearsByModel(Long id) {
		List<Year> years = yearService.getYearByModelId(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(years);
	}
	
	
	// api month
	@PostMapping("months")
	public ResponseEntity<ApiResponse> addMonth(@Valid @RequestBody  DaoMonth month) {
		
		monthService.saveMonth(month);
		ApiResponse api  = new ApiResponse(true, " le mois de mise en circulation  est ajoutée ");
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(api);
	}
	
	@GetMapping("months")
	public ResponseEntity<List<Month>> getAllMonthsByModel(Long id) {
		List<Month> months = monthService.findMonthByModelId(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(months);
	}
	// seller apis
	
	@PostMapping("users")
	public ResponseEntity<ApiSaveResponse> addUser(@Valid @RequestBody UserDto dto){
		
		Long id = userService.addUser(dto);
		ApiSaveResponse apiSaveRes = new ApiSaveResponse(true, "Utilisateur ajouté avec succès",id) ;
		return ResponseEntity.status(HttpStatus.CREATED).body(apiSaveRes);
		
	}
	
	@GetMapping("users")
	public ResponseEntity<DtoResponseUser> getUserById(@Valid @RequestParam("id") Long id){
		Seller user = userService.findUserById(id);
		DtoResponseUser ud = new DtoResponseUser(
				user.getId(),
				user.getSellerName().getName(),
				user.getSellerType(), 
				user.getEmail(),
				user.getPhone(), 
				user.getCity(),
				user.getAddress());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(ud);
	}
	
	
	@PostMapping("names")
	public ResponseEntity<ApiResponse> addSellerName(@Valid @RequestBody SellerName sellerName) {
		sellerNameService.saveName(sellerName);
		ApiResponse api = new ApiResponse(true, "name de vendeur est ajouteé avec succes");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(api);
	}
	
	
	@GetMapping("names")
	public  ResponseEntity<List<SellerNameDto>> getAllNames() {
		List<SellerNameDto> names = sellerNameService.getAllNames();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(names);
	}
	
	//cars apis
	
	@PostMapping("/cars")
	public ResponseEntity<ApiSaveResponse> addCar(
			@Valid @RequestBody CarFormData dto) throws Exception {
		System.out.print(dto);
		Long car_id = carService.addCar(dto);
		ApiSaveResponse apiRes = new ApiSaveResponse(true, "Voiture ajoutée avec succès",car_id);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiRes);
	}
	
	
	@PostMapping(value = "/cars/{id}/image")
	public ResponseEntity<ApiResponse> uploadImage(
			@PathVariable Long id,
			@RequestPart(value = "file",required = true) MultipartFile file) throws Exception{
		
		carService.saveImage(id, file);
	
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ApiResponse(true, "Image uploadée avec succès"));
	}
	
	
	@GetMapping("/cars")
	public ResponseEntity<List<CarResponseDto>> getAllCars(){
		List<CarResponseDto> cars = carService.getAllCars();
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cars);
	}
}
