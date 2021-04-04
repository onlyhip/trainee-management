package com.edu.training.repositories;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.edu.training.dto.TraineeScoreDTO;
import com.edu.training.entities.Trainee;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class TraineeRepositoryImpl implements CustomTraineeRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<TraineeScoreDTO> findAvgScoreTraineeByCourseId(int courseId) {
        TypedQuery<TraineeScoreDTO> query = em.createQuery(
                "SELECT new com.edu.training.dto.TraineeScoreDTO(t.id, t.university, t.account, t.email, t.name, AVG(s.value)) FROM Trainee t, Score s WHERE t.course.id = :courseId AND t.id = s.primaryKey.IdTrainee GROUP BY t.id, t.university, t.account, t.email, t.name",
                TraineeScoreDTO.class);
        query.setParameter("courseId", courseId);
        List<TraineeScoreDTO> trainees = query.getResultList();
        return trainees;
    }

}
