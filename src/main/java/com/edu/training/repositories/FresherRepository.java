package com.edu.training.repositories;

import com.edu.training.entities.Fresher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, Integer>{
    
}
