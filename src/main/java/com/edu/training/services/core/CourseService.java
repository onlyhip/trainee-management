package com.edu.training.services.core;

import com.edu.training.entities.Course;

import org.springframework.data.domain.Page;


public interface CourseService extends IService<Course> {
    Page<Course> findPaginated(int pageNo, int pageSize, String sortField);
    Page<Course> findPaginated(int pageNo, int pageSize);

}
