package com.edu.training.services.implementation;

import com.edu.training.entities.Trainee;
import com.edu.training.services.core.TraineeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TraineeServiceImpl implements TraineeService {

    @Override
    public void save(Trainee trainee) {

    }

    @Override
    public void update(Trainee trainee) {

    }

    @Override
    public void delete(long theId) {

    }

    @Override
    public Trainee findById(long theId) {
        return null;
    }

    @Override
    public List<Trainee> getAll() {
        return null;
    }
//
//    @Override
//    public Page<TraineeScoreDto> findPaginated(int pageNo, int idCourse) {
//        Pageable pageable = PageRequest.of(pageNo - 1, 10);
//        return traineeRepository.findScoreByTrainee(idCourse, pageable);
//    }

}
