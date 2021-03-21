package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ClassAdmin")
public class ClassAdmin implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Id")
    @MapsId
    private User userOTO3;

    @Column(name = "Password")
    private String password;

    public ClassAdmin(int id, User userOTO3, String password) {
        this.id = id;
        this.userOTO3 = userOTO3;
        this.password = password;
    }

    public ClassAdmin() {
    }

    public ClassAdmin(int id) {
        this.id = id;
    }

    public ClassAdmin(User userOTO3, String password) {
        this.userOTO3 = userOTO3;
        this.password = password;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUserOTO3() {
        return userOTO3;
    }

    public void setUserOTO3(User userOTO3) {
        this.userOTO3 = userOTO3;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClassAdmin(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClassAdmin [id=" + id + ", password=" + password + ", userOTO3=" + userOTO3 + "]";
    }

    

}
