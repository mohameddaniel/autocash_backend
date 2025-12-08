package com.autocash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autocash.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
}
