package com.edu.training.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ClassAdmin")
public class ClassAdmin extends User{

    @Column(name = "Password")
    private String password;

    public ClassAdmin() {
        super();
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
        return "ClassAdmin [password: " + password + "account: " +  super.getAccount() + "]";
    }

}
