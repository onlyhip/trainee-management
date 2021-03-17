package com.edu.training.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer")
public class Trainer implements Serializable{
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // @Id
    // @OneToOne
    // @JoinColumn(name="Id", referencedColumnName="Id")
    // private User userOTO2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Id")
    @MapsId
    private User userOTO2;


    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Course> courseList;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TrainingObjective> trainingObjectives;

    public Trainer() {
    }

    public Trainer(List<Course> courseList, List<TrainingObjective> trainingObjectives) {
        this.courseList = courseList;
        this.trainingObjectives = trainingObjectives;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<TrainingObjective> getTrainingObjectives() {
        return trainingObjectives;
    }

    public void setTrainingObjectives(List<TrainingObjective> trainingObjectives) {
        this.trainingObjectives = trainingObjectives;
    }

    public User getUserOTO() {
        return userOTO2;
    }

    public void setUserOTO(User userOTO2) {
        this.userOTO2 = userOTO2;
    }

    public Trainer(User userOTO) {
        this.userOTO2 = userOTO;
    }

    public Trainer(User userOTO, List<Course> courseList, List<TrainingObjective> trainingObjectives) {
        this.userOTO2 = userOTO;
        this.courseList = courseList;
        this.trainingObjectives = trainingObjectives;
    }

}
