package com.edu.training.dto;

public class TraineeScoreDto {

    private int id;

    private String name;

    private String account;

    private float score;

    private String email;

    private String university;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }


    public TraineeScoreDto(int id, String name, String account, float score, String email, String university) {
        this.id = id;
        this.name = name;
        this.account = account;
        this.score = score;
        this.email = email;
        this.university = university;
    }

    public TraineeScoreDto() {
    }
}

