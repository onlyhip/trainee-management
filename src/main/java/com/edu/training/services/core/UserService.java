package com.edu.training.services.core;

import com.edu.training.entities.User;

import org.springframework.data.domain.Page;

public interface UserService extends IService<User> {
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);    

    
}
