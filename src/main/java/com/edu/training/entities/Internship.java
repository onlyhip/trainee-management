package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Internship")
public class Internship implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    // @Id
    // @OneToOne
    // @JoinColumn(name="Id", referencedColumnName="Id")
    // private Trainee traineeOTO;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "Id")
    @MapsId
    private Trainee traineeOTO;

    public Internship() {
    }

    public Internship(Trainee traineeOTO) {
        this.traineeOTO = traineeOTO;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Trainee getTraineeOTO() {
        return traineeOTO;
    }

    public void setTraineeOTO(Trainee traineeOTO) {
        this.traineeOTO = traineeOTO;
    }

    

}
