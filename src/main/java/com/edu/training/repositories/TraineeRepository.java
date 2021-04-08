package com.edu.training.repositories;

import com.edu.training.entities.Trainee;

import com.edu.training.models.TraineeScoreDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    /**
     * 
     * @param courseId
     * @return
     */
    @Query("SELECT COUNT(t.course) FROM Trainee t WHERE t.course.id = ?1")
    int countCourseByCourseId(int courseId);
    
    /**
     * Find the list of trainee infor and trainee average score of 1 course
     * @param idCourse is ID of course
     * @return list of trainee infor
     */
    @Query(name = "find_trainee_score_dto", nativeQuery = true)
    List<TraineeScoreDto> findScoreByTrainee(@Param("idCourse") int idCourse);

    /**
     * find the avg score and infor of all trainee  
     * @return list of trainee infor with score 
     */
    @Query(name = "find_all_trainee_score_dto", nativeQuery = true)
    List<TraineeScoreDto> findScoreByAllTrainee();

}
