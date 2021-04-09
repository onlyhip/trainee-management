package com.edu.training.repositories;

import com.edu.training.entities.Status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Integer> {

    /**
     * Find the status by it's Type 
     * @param type is type attribute in status
     */
    Status findStatusByType(String type);

}
