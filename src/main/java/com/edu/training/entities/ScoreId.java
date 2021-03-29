package com.edu.training.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ScoreId implements Serializable {
    
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private int id;

    @Column(name = "Id_to")
    private int IdTO;

    @Column(name = "Id_trainee")
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

    

    public static long getSerialversionuid() {
        return serialVersionUID;
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
