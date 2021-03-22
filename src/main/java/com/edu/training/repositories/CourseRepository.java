package com.edu.training.repositories;

import com.edu.training.entities.Course;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    
    

}
