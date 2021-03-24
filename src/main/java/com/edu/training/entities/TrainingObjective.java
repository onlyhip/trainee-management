package com.edu.training.entities;

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
@Table(name = "TrainingObjective")
public class TrainingObjective {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTrainer", referencedColumnName = "Id")
    private Trainer trainer;

    @OneToMany(mappedBy = "trainingObjective", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<FeedBack> feedBacks;

    @OneToMany(mappedBy = "trainingObjective", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Score> listScore;

    public TrainingObjective(int id, String name, String code, Trainer trainer, List<FeedBack> feedBacks,
            List<Score> listScore) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.trainer = trainer;
        this.feedBacks = feedBacks;
        this.listScore = listScore;
    }

    public TrainingObjective(int id, String name, String code, Trainer trainer) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.trainer = trainer;
    }

    public TrainingObjective(int id) {
        this.id = id;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<FeedBack> getFeedBacks() {
        return feedBacks;
    }

    public void setFeedBacks(List<FeedBack> feedBacks) {
        this.feedBacks = feedBacks;
    }

    public List<Score> getListScore() {
        return listScore;
    }

    public void setListScore(List<Score> listScore) {
        this.listScore = listScore;
    }

    

}
