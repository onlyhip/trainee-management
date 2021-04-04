package com.edu.training.services.implementation;

import java.util.List;

import com.edu.training.entities.Course;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.services.core.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(Course t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Course t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(long theId) {
        // TODO Auto-generated method stub

    }

    @Override
    public Course findById(long theId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Course> getAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Course> findPaginated(int pageNo, int pageSize, String sortField) {
        Sort sort = Sort.by(sortField).ascending();
        // Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return courseRepository.findAll(pageable);
    }

    @Override
    public Page<Course> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return courseRepository.findAll(pageable);
    }


}
