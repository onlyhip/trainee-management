package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Score")
public class Score implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Id
    @JoinColumn(name = "IdTO", referencedColumnName="Id")
    private TrainingObjective trainingObjective;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @Id
    @JoinColumn(name = "IdTrainee", referencedColumnName="Id")
    private Trainee trainee;

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private float value;

    // private String note;

    public Score(int id, TrainingObjective trainingObjective, Trainee trainee, String name, float value) {
        this.id = id;
        this.trainingObjective = trainingObjective;
        this.trainee = trainee;
        this.name = name;
        this.value = value;
    }

    public Score(int id) {
        this.id = id;
    }

    public Score(TrainingObjective trainingObjective, Trainee trainee, String name, float value) {
        this.trainingObjective = trainingObjective;
        this.trainee = trainee;
        this.name = name;
        this.value = value;
    }

    public Score(Trainee trainee, String name, float value) {
        this.trainee = trainee;
        this.name = name;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainingObjective getTrainingObjective() {
        return trainingObjective;
    }

    public void setTrainingObjective(TrainingObjective trainingObjective) {
        this.trainingObjective = trainingObjective;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
    
}
