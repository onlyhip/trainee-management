package com.edu.training.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Fresher")
public class Fresher implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // @Id
    // @OneToOne
    // @JoinColumn(name="Id", referencedColumnName="Id")
    // private Trainee traineeOTO;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Id")
    @MapsId
    private Trainee traineeOTO;

    @Column(name = "UniversityGraduationDate")
    private Date universityGraduationDate;

    @Column(name = "FullTimeAvailable")
    private Date fullTimeWorkingAvailable;

    public Fresher(Date universityGraduationDate, Date fullTimeWorkingAvailable) {
        this.universityGraduationDate = universityGraduationDate;
        this.fullTimeWorkingAvailable = fullTimeWorkingAvailable;
    }

    public Fresher() {
    }

    public Date getUniversityGraduationDate() {
        return universityGraduationDate;
    }

    public void setUniversityGraduationDate(Date universityGraduationDate) {
        this.universityGraduationDate = universityGraduationDate;
    }

    public Date getFullTimeWorkingAvailable() {
        return fullTimeWorkingAvailable;
    }

    public void setFullTimeWorkingAvailable(Date fullTimeWorkingAvailable) {
        this.fullTimeWorkingAvailable = fullTimeWorkingAvailable;
    }

    public Fresher(Trainee traineeOTO, Date universityGraduationDate, Date fullTimeWorkingAvailable) {
        this.traineeOTO = traineeOTO;
        this.universityGraduationDate = universityGraduationDate;
        this.fullTimeWorkingAvailable = fullTimeWorkingAvailable;
    }

    public Fresher(Trainee traineeOTO) {
        this.traineeOTO = traineeOTO;
    }

    public Trainee getTraineeOTO() {
        return traineeOTO;
    }

    public void setTraineeOTO(Trainee traineeOTO) {
        this.traineeOTO = traineeOTO;
    }

}
