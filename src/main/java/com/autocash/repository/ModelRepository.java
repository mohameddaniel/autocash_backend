package com.autocash.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autocash.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

	@Query(value = "SELECT * FROM model WHERE brand_id= :id",nativeQuery = true)
	List<Model> getAllModelByBrand(@Param("id") Long id);
	
	
	
	
}
