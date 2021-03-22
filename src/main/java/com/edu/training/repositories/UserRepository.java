package com.edu.training.repositories;


import com.edu.training.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    @Query("SELECT u FROM User u, ClassAdmin a WHERE u.id = a.id AND u.account = ?1")
    User findByAccountClassAdmin(String usernameOfCA);

    @Query("SELECT a.password FROM User u, ClassAdmin a WHERE u.id = a.id AND u.account = ?1")
    String findPasswordByAccountClassAdmin(String usernameOfCA);

    @Query("SELECT u.id FROM User u WHERE u.account = ?1")
    int findByAccount(String username);

}
