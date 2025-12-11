package com.autocash.services;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.autoCash.dto.DaoMonth;
import com.autocash.entity.Model;
import com.autocash.entity.Month;
import com.autocash.repository.MonthRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MonthService {
	private final MonthRepository monthRepo;
	private final ModelService modelService;
	
	@Transactional(readOnly = true)
	public List<Month> findMonthByModelId (Long id) {
		List<Month> m =  monthRepo.getAllMonthByModelId(id);
		return m;
	}
	
	@Transactional(readOnly = true)
	public Month findMonthBylId (Long id) {
		Month m =  monthRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Id de month introuvalble"));
		return m;
	}
	
	
	@Transactional
	public void saveMonth(DaoMonth dto) {
		
		Model model  = modelService.getModelById(dto.getModel_id());
		Month month = new Month();
		month.setMonth(dto.getMonth());
		month.setModel(model);
		monthRepo.save(month);
	}
}
