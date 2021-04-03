package com.edu.training.dto;

import com.edu.training.entities.Trainee;

public class TraineeScoreDTO {
    
    private Trainee trainee;

    private double avgScore;

    public TraineeScoreDTO() {
    }

    public TraineeScoreDTO(Trainee trainee, double avgScore) {
        this.trainee = trainee;
        this.avgScore = avgScore;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    
}
