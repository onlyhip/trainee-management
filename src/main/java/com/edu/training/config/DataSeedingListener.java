package com.edu.training.config;

import java.util.HashSet;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.Role;
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
    private ClassAdminRepository classAdminRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

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
            ClassAdmin admin = new ClassAdmin(passwordEncoder.encode("admin"));
            admin.setAccount("admin");
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ROLE_ADMIN"));
            roles.add(roleRepository.findByName("ROLE_MEMBER"));
            admin.setRoles(roles);
            // userRepository.save(admin); 
            classAdminRepository.save(admin);
        }

    }

}
