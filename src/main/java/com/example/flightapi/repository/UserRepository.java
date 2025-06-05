/**
 * User Repository
 * Copyright (C) 2025
 * Created by hanxu on 2025/05/24.
 */
package com.example.flightapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flightapi.entity.UserEntity;


/**
 * User Repository
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Check if user exists by email
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * Find user by email
     * @param email
     * @return
     */
    UserEntity findByEmail(String email);
}
