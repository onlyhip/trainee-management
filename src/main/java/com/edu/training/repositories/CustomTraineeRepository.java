package com.edu.training.repositories;

import java.util.List;

import com.edu.training.dto.TraineeScoreDTO;

public interface CustomTraineeRepository {
    public List<TraineeScoreDTO> findAvgScoreTraineeByCourseId(int courseId);
}
