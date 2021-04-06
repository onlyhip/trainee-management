package com.edu.training.repositories;

import com.edu.training.dto.TOScoreDto;
import com.edu.training.entities.Trainee;

import com.edu.training.models.TraineeScoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    @Query("SELECT COUNT(t.course) FROM Trainee t WHERE t.course.id = ?1")
    int countCourseByCourseId(int courseId);
    
    @Query(name = "find_trainee_score_dto", nativeQuery = true)
    List<TraineeScoreDto> findScoreByTrainee(@Param("idCourse") int idCourse);

    @Query(name = "find_all_trainee_score_dto", nativeQuery = true)
    List<TraineeScoreDto> findScoreByAllTrainee();

}
