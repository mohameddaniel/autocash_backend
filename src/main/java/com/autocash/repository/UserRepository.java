package com.autocash.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autocash.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
