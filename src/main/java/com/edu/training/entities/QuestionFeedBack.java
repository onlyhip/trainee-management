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
@Table(name = "QuestionFeedback")
public class QuestionFeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdFeedback", referencedColumnName = "Id")
    private FeedBack feedback;

    @Column(name = "groupOfQuestion")
    private String groupOfQuestion;

    @Column(name = "Topic")
    private String topic;

    @Column(name = "QfbContent")
    private String content;

    @Column(name = "Score")
    private float score;

    public QuestionFeedBack(int id, FeedBack feedback, String groupOfQuestion, String topic, String content,
            float score) {
        this.id = id;
        this.feedback = feedback;
        this.groupOfQuestion = groupOfQuestion;
        this.topic = topic;
        this.content = content;
        this.score = score;
    }

    public QuestionFeedBack(FeedBack feedback, String groupOfQuestion, String topic, String content, float score) {
        this.feedback = feedback;
        this.groupOfQuestion = groupOfQuestion;
        this.topic = topic;
        this.content = content;
        this.score = score;
    }

    public QuestionFeedBack(int id, String groupOfQuestion, String topic, String content, float score) {
        this.id = id;
        this.groupOfQuestion = groupOfQuestion;
        this.topic = topic;
        this.content = content;
        this.score = score;
    }

    public QuestionFeedBack(String groupOfQuestion, String topic, String content, float score) {
        this.groupOfQuestion = groupOfQuestion;
        this.topic = topic;
        this.content = content;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FeedBack getFeedback() {
        return feedback;
    }

    public void setFeedback(FeedBack feedback) {
        this.feedback = feedback;
    }

    public String getGroupOfQuestion() {
        return groupOfQuestion;
    }

    public void setGroupOfQuestion(String groupOfQuestion) {
        this.groupOfQuestion = groupOfQuestion;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    

}
