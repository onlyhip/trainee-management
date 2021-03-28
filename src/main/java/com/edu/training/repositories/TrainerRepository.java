package com.edu.training.repositories;

import com.edu.training.entities.Trainer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

}