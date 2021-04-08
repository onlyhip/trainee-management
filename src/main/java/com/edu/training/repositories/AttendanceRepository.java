package com.edu.training.repositories;

import java.util.List;

import com.edu.training.entities.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    
    /**
     * Find the number of present in total attendance check 
     * @param traineeId is ID of trainee who user want to check 
     * @return number of total present dates 
     */
    @Query("SELECT COUNT(a.user.id) FROM Attendance a WHERE a.user.id = ?1 AND a.type != 1 AND a.type != 4 GROUP BY a.user.id")
    int findPresentAttendanceByTraineeId(int traineeId);

    /**
     * Find total attendance check 
     * @param traineeId is ID of trainee who user want to check
     * @return number of total attendance checks
     */
    @Query("SELECT COUNT(a.user.id) FROM Attendance a WHERE a.user.id = ?1 GROUP BY a.user.id")
    int findTotalAttendanceByTraineeId(int traineeId);

    /**
     * Find all the attendance infor of a trainee
     * @param traineeId is ID of trainee who user want to find
     * @return List of Attendance infor of trainee
     */
    @Query("SELECT a FROM Attendance a WHERE a.user.id = ?1")
    List<Attendance> findAttendanceByTraineeId(int traineeId);
}
