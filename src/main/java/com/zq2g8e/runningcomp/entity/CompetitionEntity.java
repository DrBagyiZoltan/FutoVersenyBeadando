package com.zq2g8e.runningcomp.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "competition")
public class CompetitionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long competitionId;
    private String competitionName;
    private float distance;

    public CompetitionEntity() {
    }

    public CompetitionEntity(String competitionName, float distance) {
        this.competitionName = competitionName;
        this.distance = distance;
    }

    public long getCompetitionId() {
        return competitionId;
    }

    public float getDistance() {
        return distance;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    // updateRace will use it
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
