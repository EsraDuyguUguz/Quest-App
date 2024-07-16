package com.project.questapp2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.questapp2.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    RefreshToken findByUserId(Long userId);

}
