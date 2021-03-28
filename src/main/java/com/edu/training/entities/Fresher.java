package com.edu.training.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "Fresher")
public class Fresher extends Trainee{

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

    

}
