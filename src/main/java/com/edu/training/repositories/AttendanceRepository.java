package com.edu.training.repositories;

import java.util.List;

import com.edu.training.entities.Attendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    
    @Query("SELECT COUNT(a.user.id) FROM Attendance a WHERE a.user.id = ?1 AND a.type != 1 AND a.type != 4 GROUP BY a.user.id")
    int findPresentAttendanceByTraineeId(int traineeId);

    @Query("SELECT COUNT(a.user.id) FROM Attendance a WHERE a.user.id = ?1 GROUP BY a.user.id")
    int findTotalAttendanceByTraineeId(int traineeId);

    @Query("SELECT a FROM Attendance a WHERE a.user.id = ?1")
    List<Attendance> findAttendanceByTraineeId(int traineeId);
}
