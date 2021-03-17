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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Course")
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "openDate")
    private Date openDate;

    @Column(name = "Note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdTrainer", nullable = false, referencedColumnName = "Id")
    private Trainer trainer;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Trainee> trainee;

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
    
    

}
