package com.edu.training.entities;

import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Score> score;

}