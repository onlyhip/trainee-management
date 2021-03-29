package com.edu.training.repositories;

import com.edu.training.entities.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

}