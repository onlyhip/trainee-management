package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ScoreId {
    
    private int id;
    private int IdTO;
    private int IdTrainee;
    
    public ScoreId() {
    }
    

    
    public int getIdTO() {
        return IdTO;
    }

    public void setIdTO(int idTO) {
        IdTO = idTO;
    }

    public int getIdTrainee() {
        return IdTrainee;
    }

    public void setIdTrainee(int idTrainee) {
        IdTrainee = idTrainee;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }



    public ScoreId(int id, int idTO, int idTrainee) {
        this.id = id;
        IdTO = idTO;
        IdTrainee = idTrainee;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + IdTO;
        result = prime * result + IdTrainee;
        result = prime * result + id;
        return result;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ScoreId other = (ScoreId) obj;
        if (IdTO != other.IdTO)
            return false;
        if (IdTrainee != other.IdTrainee)
            return false;
        if (id != other.id)
            return false;
        return true;
    }
    
    

}
