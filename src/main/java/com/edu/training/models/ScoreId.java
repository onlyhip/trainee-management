package com.edu.training.models;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.edu.training.entities.Trainee;
import com.edu.training.entities.TrainingObjective;

import java.io.Serializable;

import javax.persistence.CascadeType;

@Embeddable
public class ScoreId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL)
    private Trainee trainee;

    @ManyToOne(cascade = CascadeType.ALL)
    private TrainingObjective trainingObjective;

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public TrainingObjective getTrainingObjective() {
        return trainingObjective;
    }

    public void setTrainingObjective(TrainingObjective trainingObjective) {
        this.trainingObjective = trainingObjective;
    }

}
