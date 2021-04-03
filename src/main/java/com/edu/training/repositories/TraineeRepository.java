package com.edu.training.repositories;

import java.util.List;

import com.edu.training.dto.TraineeScoreDTO;
import com.edu.training.entities.Trainee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    StringBuilder sb = new StringBuilder();
    
    @Query("SELECT COUNT(t.course) FROM Trainee t WHERE t.course.id = ?1")
    public int countCourseByCourseId(int courseId);

    @Query("SELECT t FROM Trainee t WHERE t.course.id = ?1")
    public List<Trainee> findTraineeByCourseId(int courseId);

    // @Query("SELECT t, R.avgS as avgScore  FROM (SELECT t.id, AVG(s.value) AS avgS FROM Trainee t, Score s WHERE t.course.id = ?1 AND t.id = s.primaryKey.IdTrainee GROUP BY t.id) R, Trainee t WHERE t.id = R.id")
    // public List<TraineeScoreDTO> findAvgScoreTraineeByCourseId(int courseId);

    

}
