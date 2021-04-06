package com.edu.training.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.*;

import com.edu.training.dto.TOScoreDto;
import com.edu.training.models.ScoreId;

@Entity
@Table(name = "Score")
@AssociationOverrides({
        @AssociationOverride(name = "primaryKey.trainee", joinColumns = @JoinColumn(name = "IdTrainee")),
        @AssociationOverride(name = "primaryKey.trainingObjective", joinColumns = @JoinColumn(name = "IdTO")) })
@NamedNativeQueries({
        @NamedNativeQuery(name = "find_to_score_dto", query = "SELECT" + " s.name AS name," + " AVG(s.value) AS score"
                + " FROM score s "
                + " WHERE s.id_trainee = :idTrainee "
                + " GROUP BY s.name", resultSetMapping = "to_score_dto") })
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "to_score_dto", classes = @ConstructorResult(targetClass = TOScoreDto.class, columns = {
                @ColumnResult(name = "name", type = String.class)
                , @ColumnResult(name = "score", type = Double.class) })) })
public class Score {

    @EmbeddedId
    private ScoreId primaryKey = new ScoreId();

    @Column(name = "Name")
    private String name;

    @Column(name = "Value")
    private float value;

    public Score() {
    }

    public Score(ScoreId primaryKey, String name, float value) {
        this.primaryKey = primaryKey;
        this.name = name;
        this.value = value;
    }

    public ScoreId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ScoreId primaryKey) {
        this.primaryKey = primaryKey;
    }

    @Transient
    public Trainee getTrainee() {
        return getPrimaryKey().getTrainee();
    }

    public void setTrainee(Trainee trainee) {
        getPrimaryKey().setTrainee(trainee);
    }

    @Transient
    public TrainingObjective getTrainingObjective() {
        return getPrimaryKey().getTrainingObjective();
    }

    public void setTrainingObjective(TrainingObjective trainingObjective) {
        getPrimaryKey().setTrainingObjective(trainingObjective);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((primaryKey == null) ? 0 : primaryKey.hashCode());
        result = prime * result + Float.floatToIntBits(value);
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
        Score other = (Score) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (primaryKey == null) {
            if (other.primaryKey != null)
                return false;
        } else if (!primaryKey.equals(other.primaryKey))
            return false;
        if (Float.floatToIntBits(value) != Float.floatToIntBits(other.value))
            return false;
        return true;
    }

}
