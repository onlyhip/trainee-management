package com.edu.training.repositories;

import com.edu.training.entities.Score;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Score, Integer> {
    
}
