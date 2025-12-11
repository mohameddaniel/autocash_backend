package com.autocash.repository;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.autocash.entity.Year;

public interface YearRepository extends JpaRepository<Year, Long> {

	@Query(value = "SELECT * FROM year WHERE model_id = :id", nativeQuery = true)
	List<Year> findAllYearByModelId(@Param("id") Long id);
}
