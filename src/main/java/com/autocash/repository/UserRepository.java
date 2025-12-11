package com.autocash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autocash.entity.Seller;

public interface UserRepository extends JpaRepository<Seller, Long> {

}
