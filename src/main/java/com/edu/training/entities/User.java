package com.edu.training.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User implements Serializable{
   
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;
    
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Column(name="Account")
    private String account;

    @Column(name="InNational")
    private String national;

    @Column(name="FullName")
    private String name;

    @Column(name="Email")
    private String email;

    @Column(name="TelPhone")
    private String telNumber;

    @Column(name="Facebook")
    private String facebook;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userOTO1")
    private Trainee trainee;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userOTO2")
    private Trainer trainer;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userOTO3")
    private ClassAdmin classAdmin;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Attendance> attendanceList;

    public User() {
        super();
    }

    public User(int id, String national, String account, String name, List<Attendance> attendanceList, String telNumber,
            String facebook, String email) {
        this.id = id;
        this.national = national;
        this.account = account;
        this.name = name;
        this.attendanceList = attendanceList;
        this.telNumber = telNumber;
        this.facebook = facebook;
        this.email = email;
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String national, String account, String name, String telNumber, String facebook, String email) {
        this.id = id;
        this.national = national;
        this.account = account;
        this.name = name;
        this.telNumber = telNumber;
        this.facebook = facebook;
        this.email = email;
    }

    public User(String national, String account, String name, List<Attendance> attendanceList, String telNumber,
            String facebook, String email) {
        this.national = national;
        this.account = account;
        this.name = name;
        this.attendanceList = attendanceList;
        this.telNumber = telNumber;
        this.facebook = facebook;
        this.email = email;
    }

    public User(String national, String account, String name, String telNumber, String facebook, String email) {
        this.national = national;
        this.account = account;
        this.name = name;
        this.telNumber = telNumber;
        this.facebook = facebook;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Attendance> getAttendanceList() {
        return attendanceList;
    }

    public void setAttendanceList(List<Attendance> attendanceList) {
        this.attendanceList = attendanceList;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public ClassAdmin getClassAdmin() {
        return classAdmin;
    }

    public void setClassAdmin(ClassAdmin classAdmin) {
        this.classAdmin = classAdmin;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [account=" + account + ", attendanceList=" + attendanceList + ", classAdmin=" + classAdmin
                + ", email=" + email + ", facebook=" + facebook + ", id=" + id + ", name=" + name + ", national="
                + national + ", roles=" + roles + ", telNumber=" + telNumber + ", trainee=" + trainee + ", trainer="
                + trainer + "]";
    }
    
    

}
