package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.core.sym.Name;

@Entity
@Table(name = "Score")
public class Score implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "id")
    private int id;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "IdTrainee", referencedColumnName="id")
    private Trainee trainee;

    @ManyToOne
    @MapsId
    @JoinColumn(name = "IdTO", referencedColumnName = "id")
    private TrainingObjective trainingObjective;

    // @EmbeddedId
    // private ScoreId scoreId = new ScoreId();
    // @ManyToOne
    // @MapsId("IdTrainee")
    // @JoinColumn(name = "IdTrainee")
    // private Trainee trainee;
    // @ManyToOne
    // @MapsId("IdTO")
    // @JoinColumn(name = "IdTO")
    // private TrainingObjective trainingObjective;

    

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private float value;

    // private String note;

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

    public Score() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
