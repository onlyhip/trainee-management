package com.edu.training.repositories;

import java.util.List;

import com.edu.training.entities.Trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    @Query("SELECT COUNT(t.course) FROM Trainee t WHERE t.course.id = ?1")
    public int countCourseByCourseId(int courseId);

    @Query("SELECT t FROM Trainee t WHERE t.course.id = ?1")
    public List<Trainee> findTraineeByCourseId(int courseId);

}
