package com.edu.training.dto;

public class TOScoreDto {
    private String name;

    private double score;

    public TOScoreDto(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public TOScoreDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        
        double scale = Math.pow(10, 1);
        return Math.round(score * scale) / scale;
    }

    public void setScore(double score) {
        this.score = score;
    }

    
}
