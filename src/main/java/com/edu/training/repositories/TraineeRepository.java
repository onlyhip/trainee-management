package com.edu.training.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.edu.training.dto.TraineeScoreDTO;
import com.edu.training.entities.Trainee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer>, CustomTraineeRepository {

    @Query("SELECT COUNT(t.course) FROM Trainee t WHERE t.course.id = ?1")
    public int countCourseByCourseId(int courseId);

    @Query("SELECT t FROM Trainee t WHERE t.course.id = ?1")
    public List<Trainee> findTraineeByCourseId(int courseId);

    // @Query("SELECT t AVG(s.value) AS avgS FROM Trainee t, Score s WHERE t.course.id = ?1 AND t.id = s.primaryKey.IdTrainee GROUP BY t")
    // public List<TraineeScoreDTO> findAvgScoreTraineeByCourseId(int courseId);

   //  @Query(name = "find_trainee_score_dto", nativeQuery = true)
   //  public List<TraineeScoreDTO> findAvgScoreTraineeByCourseId(
   //    @Param("courseId") int courseId
   // );

}
