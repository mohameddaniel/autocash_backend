package com.autocash.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autocash.entity.Month;

public interface MonthRepository extends JpaRepository<Month,Long> {
	
	@Query(value = "SELECT * FROM  month WHERE model_id= :id" ,nativeQuery = true)
	List<Month> getAllMonthByModelId(@Param("id") Long id);
}
