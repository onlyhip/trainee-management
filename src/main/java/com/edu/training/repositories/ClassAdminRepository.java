package com.edu.training.repositories;

import com.edu.training.entities.ClassAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
@Repository
public interface ClassAdminRepository extends JpaRepository<ClassAdmin, Integer>{
    
	/**
	 * find the ClassAdmin by account (username)
	 * @param username is the account (username) of classAdmin who need to be find 
	 * @return object of ClassAdmin class or null 
	 */
    ClassAdmin findByAccount(String username);

	/**
	 * find the id by account (username)
	 * @param username is the account (username) they need to find 
	 * @return the id in database table of user
	 */
    @Query("SELECT a.id FROM ClassAdmin a WHERE a.account = ?1")
    int findIdByAccount(String username);

	/**
	 * Get the current Logined Account User
	 * @return the ClassAdmin object or null
	 */
    public default ClassAdmin getLoginedAccount() {
		String loginedAccount = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = findIdByAccount(loginedAccount);
		ClassAdmin loginedUser = getOne(id);
		return loginedUser;
	}

}
