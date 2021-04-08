package com.edu.training.repositories;

import java.util.List;

import javax.transaction.Transactional;

import com.edu.training.dto.TOScoreDto;
import com.edu.training.entities.Score;
import com.edu.training.models.ScoreId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    
    /**
     * Insert the score by the native query 
     * @param idTrainee is ID of trainee of record Score
     * @param idTO is ID of TraningObjective of record Score
     * @param value is the score
     * @param name is the name of trainingObjective
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Score(id_trainee, idto, value, name) VALUES (:idtrainee, :idto, :value, :name)", nativeQuery = true)
    public void insertScore(@Param("idtrainee") int idTrainee,@Param("idto") int idTO,@Param("value") float value,@Param("name") String name);

    /**
     * Find the Average Score of trainee in whole Course
     * @param traineeId is the ID of trainee need to find score
     * @return average score 
     */
    @Query("SELECT AVG(s.value) FROM Score s WHERE s.primaryKey.trainee.id = :IdTrainee GROUP BY s.primaryKey.trainee.id")
    double findAvgScoreByTraineeId(@Param("IdTrainee")int traineeId);

    /**
     * Find Score each TrainingObjective name of a trainee
     * @param traineeId is ID of trainee
     * @return List of TO name and value score of that TO
     */
    @Query(name = "find_to_score_dto", nativeQuery = true)
    List<TOScoreDto> findScoreEachTOByTraineeId(@Param("idTrainee") int traineeId);

}
