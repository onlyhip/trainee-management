package com.edu.training.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Trainer")
public class Trainer extends User{

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

}
