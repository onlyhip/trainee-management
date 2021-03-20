package com.edu.training.config;

import java.util.HashSet;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.Role;
import com.edu.training.entities.User;
import com.edu.training.repositories.ClassAdminRepository;
import com.edu.training.repositories.RoleRepository;
import com.edu.training.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClassAdminRepository classAdminRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {
        // Roles
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            roleRepository.save(new Role("ROLE_ADMIN"));
        }

        if (roleRepository.findByName("ROLE_MEMBER") == null) {
            roleRepository.save(new Role("ROLE_MEMBER"));
        }

        // Admin account
        if (userRepository.findByAccountClassAdmin("admin") == null) {
            User admin = new User();
            ClassAdmin ca = new ClassAdmin(passwordEncoder.encode("admin"));
            admin.setClassAdmin(ca);
            admin.setAccount("admin");
            ca.setUserOTO3(admin);
            // ca.setUserOTO3(admin);
            // classAdminRepository.save(ca);
            // admin.setEmail("admin@gmail.com");
            // admin.setPassword(passwordEncoder.encode("123456"));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }

        // Member account
        // if (userRepository.findByEmail("member@gmail.com") == null) {
        //     User user = new User();
        //     user.setEmail("member@gmail.com");
        //     user.setPassword(passwordEncoder.encode("123456"));
        //     HashSet<Role> roles = new HashSet<>();
        //     roles.add(roleRepository.findByName("ROLE_MEMBER"));
        //     user.setRoles(roles);
        //     userRepository.save(user);
        // }
    }

}
