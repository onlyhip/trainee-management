package com.edu.training.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Trainee")
@Inheritance(strategy = InheritanceType.JOINED)
public class Trainee extends User{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCourse", referencedColumnName = "Id")
    private Course course;

    @Column(name = "Branch")
    private String branch;

    @Column(name = "ParentDepartment")
    private String parentDepartment;

    @Column(name = "RecInterviewDate")
    private Date recInterviewDate;

    @Column(name = "RecInterviewStatus")
    private String recInterviewStatus;

    @Column(name = "Note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TraineeStatus", nullable = false, referencedColumnName = "Id")
    private Status traineeStatus;

    @Column(name = "University")
    private String university;

    @Column(name = "Faculty")
    private String faculty;

    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mistake> mistakes;

    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Certificates> certificate;

    @OneToMany(mappedBy = "pk.trainee", cascade = CascadeType.ALL)
    private Set<Score> score = new HashSet<>();

    // @ManyToMany
    // @JoinTable(name = "Score", 
    //         joinColumns = { @JoinColumn(name = "trainee_id")})
    // private Set<Score> score;

    public Trainee(Course course) {
        this.course = course;
    }

    public Trainee() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Date getRecInterviewDate() {
        return recInterviewDate;
    }

    public void setRecInterviewDate(Date recInterviewDate) {
        this.recInterviewDate = recInterviewDate;
    }

    public String getRecInterviewStatus() {
        return recInterviewStatus;
    }

    public void setRecInterviewStatus(String recInterviewStatus) {
        this.recInterviewStatus = recInterviewStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Status getTraineeStatus() {
        return traineeStatus;
    }

    public void setTraineeStatus(Status traineeStatus) {
        this.traineeStatus = traineeStatus;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<Mistake> getMistakes() {
        return mistakes;
    }

    public void setMistakes(List<Mistake> mistakes) {
        this.mistakes = mistakes;
    }

    public List<Certificates> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<Certificates> certificate) {
        this.certificate = certificate;
    }

    public Set<Score> getScore() {
        return score;
    }

    public void setScore(Set<Score> score) {
        this.score = score;
    }

    

}