package com.edu.training.test;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.User;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.core.UserService;
import com.edu.training.services.implementation.UserServiceImpl;

public class test {
    public static void main(String[] args) {
        
        UserServiceImpl usi = new UserServiceImpl();
        
        User user = new User();
        user.setAccount("admin");
        user.setId(1);
        usi.save(user);
        ClassAdmin ca = new ClassAdmin("1234");
        ca.setUserOTO3(user);

    }
}
