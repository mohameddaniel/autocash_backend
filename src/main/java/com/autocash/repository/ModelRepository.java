package com.autocash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autocash.entity.Model;

public interface ModelRepository extends JpaRepository<Model, Long> {

}
