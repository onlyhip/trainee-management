package com.edu.training.services.implementation;

import java.util.List;

import com.edu.training.entities.User;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.FresherRepository;
import com.edu.training.repositories.InternshipRepository;
import com.edu.training.repositories.ScoreRepository;
import com.edu.training.repositories.StatusRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TrainerRepository;
import com.edu.training.repositories.TrainingObjectiveRepository;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.core.UserService;
import com.edu.training.utils.data.CreateData;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired 
    private TraineeRepository traineeRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private TrainingObjectiveRepository toRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void update(User t) {
    }

    
    public User findById(Integer theId) {
        return userRepository.findById(theId).orElse(null);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    
    public void delete(Integer theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepository.findAll(pageable); 
    }

    @Override
    public User findById(long theId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(long theId) {
        // TODO Auto-generated method stub
        
    }

    public void createData() {
        
        CreateData cd = new CreateData();
        cd.createTrainer(trainerRepository);
        cd.createCourse(trainerRepository, courseRepository);
        cd.createStatus(statusRepository);
        cd.createFresher(courseRepository, statusRepository, fresherRepository);
        cd.createInternship(courseRepository, statusRepository, internshipRepository);
        cd.createTO(trainerRepository, toRepository);
        cd.createScore(toRepository, traineeRepository, scoreRepository);
    }


    
}
