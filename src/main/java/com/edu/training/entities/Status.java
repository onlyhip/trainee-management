package com.edu.training.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Status")
public class Status {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Type")
    private String type;

    @Column(name = "StartDate")
    private Date startDay;

    @Column(name = "EndDate")
    private Date endDate;

    @Column(name = "LearnTime")
    private float learningTime;

    @OneToMany(mappedBy = "traineeStatus", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Trainee> trainee;
 
    public Status(int id, String type, Date startDay, Date endDate, float learningTime) {
        this.id = id;
        this.type = type;
        this.startDay = startDay;
        this.endDate = endDate;
        this.learningTime = learningTime;
    }

    public Status(String type, Date startDay, Date endDate, float learningTime) {
        this.type = type;
        this.startDay = startDay;
        this.endDate = endDate;
        this.learningTime = learningTime;
    }

    public Status(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(float learningTime) {
        this.learningTime = learningTime;
    }

    public List<Trainee> getTrainee() {
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }

    public Status() {
    }

    @Override
    public String toString() {
        return "Status [endDate=" + endDate + ", learningTime=" + learningTime + ", startDay=" + startDay
         + ", type=" + type + "]";
    }

    

}
