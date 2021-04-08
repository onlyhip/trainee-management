package com.edu.training.repositories;

import com.edu.training.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    /**
     * find user by username
     * @param usernameOfCA is username of class admin 
     * @return User object or null
     */
    @Query("SELECT u FROM User u, ClassAdmin a WHERE u.id = a.id AND u.account = ?1")
    User findByAccountClassAdmin(String usernameOfCA);

    /**
     * find password by username 
     * @param usernameOfCA is username (account) of class admin 
     * @return password 
     */
    @Query("SELECT a.password FROM User u, ClassAdmin a WHERE u.id = a.id AND u.account = ?1")
    String findPasswordByAccountClassAdmin(String usernameOfCA);

    /**
     * find id of user
     * @param username is account (username)
     * @return id of user
     */
    @Query("SELECT u.id FROM User u WHERE u.account = ?1")
    int findByAccount(String username);

    /**
     * check the password is correct or not
     * @param username username of class admin
     * @param oldPassword is password of class admin
     * @return true if correct, false if incorrect 
     */
    public default boolean checkOldPassword(String username, String oldPassword) {
		  return findPasswordByAccountClassAdmin(username).equals(oldPassword);
	  }

    
  
}
