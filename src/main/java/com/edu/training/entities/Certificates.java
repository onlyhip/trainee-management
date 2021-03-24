package com.edu.training.entities;

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
@Table(name = "Certificate")
public class Certificates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TraineeId", nullable = false, referencedColumnName = "Id")
    private Trainee trainee;

    @Column(name = "finalGrade")
    private Rate finalGrade;

    @Column(name = "CompletionLevel")
    private String completionLevel;

    @Column(name = "Provider")
    private String provider;

    @Column(name = "GroupOf")
    private String group;

    @Column(name = "SubGroup")
    private String subGroup;

    @Column(name = "Name")
    private String name;

    @Column(name = "Code")
    private String code;

    public Certificates() {
    }

    public Certificates(int id, Trainee trainee, Rate finalGrade, String completionLevel, String provider, String group,
            String subGroup, String name, String code) {
        this.id = id;
        this.trainee = trainee;
        this.finalGrade = finalGrade;
        this.completionLevel = completionLevel;
        this.provider = provider;
        this.group = group;
        this.subGroup = subGroup;
        this.name = name;
        this.code = code;
    }

    public Certificates(int id) {
        this.id = id;
    }

    public Certificates(Trainee trainee, Rate finalGrade, String completionLevel, String provider, String group,
            String subGroup, String name, String code) {
        this.trainee = trainee;
        this.finalGrade = finalGrade;
        this.completionLevel = completionLevel;
        this.provider = provider;
        this.group = group;
        this.subGroup = subGroup;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Rate getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(Rate finalGrade) {
        this.finalGrade = finalGrade;
    }

    public String getCompletionLevel() {
        return completionLevel;
    }

    public void setCompletionLevel(String completionLevel) {
        this.completionLevel = completionLevel;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
