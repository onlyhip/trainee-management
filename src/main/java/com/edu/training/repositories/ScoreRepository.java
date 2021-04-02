package com.edu.training.repositories;

import javax.transaction.Transactional;

import com.edu.training.entities.Score;
import com.edu.training.models.ScoreId;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScoreRepository extends JpaRepository<Score, ScoreId> {
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Score(id_trainee, idto, value, name) VALUES (:idtrainee, :idto, :value, :name)", nativeQuery = true)
    public void insertScore(@Param("idtrainee") int idTrainee,@Param("idto") int idTO,@Param("value") float value,@Param("name") String name);

}
