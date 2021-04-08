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
@Table(name="Attendance")
public class Attendance {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "IdPerson", nullable = false, referencedColumnName = "Id")
    private User user;

    @Column(name="Type")
    private TypeAttendance type;

    @Column(name="Date")
    private Date date;
    
    @Column(name="Note")
    private String note;
    
    public Attendance() {
    }

    public Attendance(TypeAttendance type, Date date, String note) {
        this.type = type;
        this.date = date;
        this.note = note;
    }

    public TypeAttendance getType() {
        return type;
    }

    public void setType(TypeAttendance type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date2) {
        this.date = (Date) date2;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Attendance(int id, User user, TypeAttendance type, Date date, String note) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.date = date;
        this.note = note;
    }

    public Attendance(User user, TypeAttendance type, Date date, String note) {
        this.user = user;
        this.type = type;
        this.date = date;
        this.note = note;
    }
    
}
