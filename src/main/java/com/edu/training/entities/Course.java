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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "openDate")
    private Date openDate;
    
    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "Note")
    private String note;

    @Column(name = "PlanCount")
    private int planCount;

    @Column(name = "CurrentCount")
    private int currCount;

    @Column(name = "status")
    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;


    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Trainee> trainee;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Issue> issues;

    public Course(int id, String name, Date openDate, String note, Trainer trainer) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.note = note;
        this.trainer = trainer;
    }

    public Course(String name, Date openDate, String note, Trainer trainer) {
        this.name = name;
        this.openDate = openDate;
        this.note = note;
        this.trainer = trainer;
    }

    public Course(int id, String name, Date openDate, String note) {
        this.id = id;
        this.name = name;
        this.openDate = openDate;
        this.note = note;
    }


    
    public Course(String name, Date openDate, String note) {
        this.name = name;
        this.openDate = openDate;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPlanCount() {
        return planCount;
    }

    public void setPlanCount(int planCount) {
        this.planCount = planCount;
    }

    public List<Trainee> getTrainee() {
        return trainee;
    }

    public void setTrainee(List<Trainee> trainee) {
        this.trainee = trainee;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course [endDate=" + endDate + ", id=" + id + ", issues=" + issues + ", name=" + name + ", note=" + note
                + ", openDate=" + openDate + ", planCount=" + planCount + ", trainee=" + trainee + ", trainer="
                + trainer + "]";
    }

    public int getCurrCount() {
        return currCount;
    }

    public void setCurrCount(int currCount) {
        this.currCount = currCount;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    

}
