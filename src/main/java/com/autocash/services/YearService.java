package com.autocash.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.DaoYear;
import com.autocash.entity.Model;
import com.autocash.entity.Year;
import com.autocash.repository.YearRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YearService {
	private final YearRepository yearRepo;
	private final ModelService modelService;
	
	@Transactional(readOnly = true)
	public List<Year> getYearByModelId(Long id) {
		List<Year> years = yearRepo.findAllYearByModelId(id);
		return years;
	}
	
	@Transactional(readOnly = true)
	public Year getYearById(Long id) {
		Year year = yearRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("ID year introuvalble"));
		return year ;
	}
	
	@Transactional
	public void saveYear(DaoYear dto) {
		Model model = modelService.getModelById(dto.getModel_id());
		Year year = new Year();
		year.setYear(dto.getYear());
		year.setModel(model);
		yearRepo.save(year);
	}
}
