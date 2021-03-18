package com.edu.training.repositories;

import com.edu.training.entities.Role;

import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    Role findByName(String name);

}
