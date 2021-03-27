package com.edu.training.repositories;

import com.edu.training.entities.ClassAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassAdminRepository extends JpaRepository<ClassAdmin, Integer>{
    
    ClassAdmin findByAccount(String username);

    @Query("SELECT a.id FROM ClassAdmin a WHERE a.account = ?1")
    int findIdByAccount(String username);
}
