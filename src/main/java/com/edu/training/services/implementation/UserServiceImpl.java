package com.edu.training.services.implementation;

import java.util.List;

import com.edu.training.entities.User;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.core.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User t) {
    }

    
    public User findById(Integer theId) {
        return userRepository.findById(theId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    
    public void delete(Integer theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable); 
    }

    @Override
    public User findById(long theId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(long theId) {
        // TODO Auto-generated method stub
        
    }

}
