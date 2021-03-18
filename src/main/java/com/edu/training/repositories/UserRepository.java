package com.edu.training.repositories;


import com.edu.training.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u, ClassAdmin a WHERE u.id = a.id AND u.account = ?1")
    User findByAccountClassAdmin(String usernameOfCA);

}
