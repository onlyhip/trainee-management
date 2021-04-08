package com.edu.training.repositories;

import com.edu.training.entities.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    /**
     * find the the roll whcich name
     * @param name is the name of roll need to find 
     * @return Role object of null
     */
    Role findByName(String name);

}
