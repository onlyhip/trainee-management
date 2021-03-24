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
@Table(name = "Feedback")
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTO", referencedColumnName = "Id")
    private TrainingObjective trainingObjective;

    @Column(name = "ConsultDate")
    private Date consultDate;

    @Column(name = "FeedBackScore")
    private float feedBackScore;

    @OneToMany(mappedBy = "feedback" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<QuestionFeedBack> questionList;

    public FeedBack(int id, TrainingObjective trainingObjective, Date consultDate, float feedBackScore,
            List<QuestionFeedBack> questionList) {
        this.id = id;
        this.trainingObjective = trainingObjective;
        this.consultDate = consultDate;
        this.feedBackScore = feedBackScore;
        this.questionList = questionList;
    }

    public FeedBack(TrainingObjective trainingObjective, Date consultDate, float feedBackScore,
            List<QuestionFeedBack> questionList) {
        this.trainingObjective = trainingObjective;
        this.consultDate = consultDate;
        this.feedBackScore = feedBackScore;
        this.questionList = questionList;
    }

    public FeedBack(Date consultDate, float feedBackScore, List<QuestionFeedBack> questionList) {
        this.consultDate = consultDate;
        this.feedBackScore = feedBackScore;
        this.questionList = questionList;
    }

    public FeedBack(Date consultDate, float feedBackScore) {
        this.consultDate = consultDate;
        this.feedBackScore = feedBackScore;
    }

    public FeedBack(int id, TrainingObjective trainingObjective, Date consultDate, float feedBackScore) {
        this.id = id;
        this.trainingObjective = trainingObjective;
        this.consultDate = consultDate;
        this.feedBackScore = feedBackScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TrainingObjective getTrainingObjective() {
        return trainingObjective;
    }

    public void setTrainingObjective(TrainingObjective trainingObjective) {
        this.trainingObjective = trainingObjective;
    }

    public Date getConsultDate() {
        return consultDate;
    }

    public void setConsultDate(Date consultDate) {
        this.consultDate = consultDate;
    }

    public float getFeedBackScore() {
        return feedBackScore;
    }

    public void setFeedBackScore(float feedBackScore) {
        this.feedBackScore = feedBackScore;
    }

    public List<QuestionFeedBack> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<QuestionFeedBack> questionList) {
        this.questionList = questionList;
    }

    

    

}
