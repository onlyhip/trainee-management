package com.edu.training.entities;

import com.edu.training.models.TraineeScoreDto;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Trainee")
@Inheritance(strategy = InheritanceType.JOINED)


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "find_trainee_score_dto",
                query =
                        "SELECT" +
                                " t.id AS id, " +
                                " u.full_name AS name, " +
                                " u.account, " +
                                " avg(s.value) as score, " +
                                " u.email, " +
                                " t.university " +
                                " FROM course c " +
                                " INNER JOIN trainee t " +
                                " ON c.id = t.id_course " +
                                " INNER JOIN score s " +
                                " ON s.id_trainee = t.id " +
                                " INNER JOIN user u " +
                                " ON u.id = t.id " +
                                " WHERE c.id = :idCourse " +
                                " GROUP BY s.id_trainee",
                resultSetMapping = "trainee_score_dto"),
        @NamedNativeQuery(
                name = "find_all_trainee_score_dto",
                query =
                        "SELECT" +
                                " t.id AS id, " +
                                " u.full_name AS name, " +
                                " u.account, " +
                                " avg(s.value) as score, " +
                                " u.email, " +
                                " t.university " +
                                " FROM course c " +
                                " INNER JOIN trainee t " +
                                " ON c.id = t.id_course " +
                                " INNER JOIN score s " +
                                " ON s.id_trainee = t.id " +
                                " INNER JOIN user u " +
                                " ON u.id = t.id " +
                                " GROUP BY s.id_trainee",
                resultSetMapping = "all_trainee_score_dto")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "trainee_score_dto",
                classes = @ConstructorResult(
                        targetClass = TraineeScoreDto.class,
                        columns = {@ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "account", type = String.class),
                                @ColumnResult(name = "score", type = Float.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "university", type = String.class)})),
        @SqlResultSetMapping(
                name = "all_trainee_score_dto",
                classes = @ConstructorResult(
                        targetClass = TraineeScoreDto.class,
                        columns = {@ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "account", type = String.class),
                                @ColumnResult(name = "score", type = Float.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "university", type = String.class)}))
})
public class Trainee extends User {

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

    @OneToMany(mappedBy = "primaryKey.trainee", cascade = CascadeType.ALL)
    private Set<Score> scores = new HashSet<Score>();


    public Trainee(Course course) {
        this.course = course;
    }

    public Trainee() {
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Date getRecInterviewDate() {
        return recInterviewDate;
    }

    public void setRecInterviewDate(Date recInterviewDate) {
        this.recInterviewDate = recInterviewDate;
    }

    public String getRecInterviewStatus() {
        return recInterviewStatus;
    }

    public void setRecInterviewStatus(String recInterviewStatus) {
        this.recInterviewStatus = recInterviewStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Status getTraineeStatus() {
        return traineeStatus;
    }

    public void setTraineeStatus(Status traineeStatus) {
        this.traineeStatus = traineeStatus;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public List<Mistake> getMistakes() {
        return mistakes;
    }

    public void setMistakes(List<Mistake> mistakes) {
        this.mistakes = mistakes;
    }

    public List<Certificates> getCertificate() {
        return certificate;
    }

    public void setCertificate(List<Certificates> certificate) {
        this.certificate = certificate;
    }

    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

}