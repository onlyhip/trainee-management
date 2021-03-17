package com.edu.training.entities;

import java.io.Serializable;
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
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Trainee")
public class Trainee implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // @Id
    // @OneToOne
    // @JoinColumn(name="Id", referencedColumnName="Id")
    // private User userOTO1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Id")
    @MapsId
    private User userOTO1;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "traineeOTO")
    private Internship internship;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "traineeOTO")
    private Fresher fresher;
    // private List<TrainingObjective> trainingObjective;

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