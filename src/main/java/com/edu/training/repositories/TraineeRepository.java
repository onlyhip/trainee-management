package com.edu.training.repositories;

import com.edu.training.entities.Trainee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
    
}
