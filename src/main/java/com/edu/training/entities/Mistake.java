package com.edu.training.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mistake")
public class Mistake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id", referencedColumnName = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String content;

    @Column(name = "Note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdTrainee", nullable = false, referencedColumnName = "Id")
    private Trainee trainee;

    @Column(name = "Date")
    private Date date;

    public Mistake(int id, String name, String content, String note, Trainee trainee, Date date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.note = note;
        this.trainee = trainee;
        this.date = date;
    }

    public Mistake(String name, String content, String note, Trainee trainee, Date date) {
        this.name = name;
        this.content = content;
        this.note = note;
        this.trainee = trainee;
        this.date = date;
    }

    public Mistake(int id, String name, String content, String note, Date date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.note = note;
        this.date = date;
    }

    public Mistake(String name, String content, String note, Date date) {
        this.name = name;
        this.content = content;
        this.note = note;
        this.date = date;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Mistake() {
    }

}
