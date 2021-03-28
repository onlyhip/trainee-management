package com.edu.training.repositories;

import com.edu.training.entities.TrainingObjective;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingObjectiveRepository extends JpaRepository<TrainingObjective, Integer> {
    
}
