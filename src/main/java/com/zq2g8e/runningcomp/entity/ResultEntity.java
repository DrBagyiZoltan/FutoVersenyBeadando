package com.zq2g8e.runningcomp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resultId;

    @JoinColumn(nullable = false)
    private float timeResult;

    @JsonIgnore
    @OneToOne
    @JoinColumn(nullable = false)
    private RunnerEntity participant;

    @OneToOne
    @JoinColumn(nullable = false)
    private CompetitionEntity competition;



    public ResultEntity() {
    }

    public long getResultId() {
        return resultId;
    }

    public float getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(float timeResult) {
        this.timeResult = timeResult;
    }

    public RunnerEntity getParticipant() {
        return participant;
    }

    public void setParticipant(RunnerEntity participant) {
        this.participant = participant;
    }

    public CompetitionEntity getCompetition() {
        return competition;
    }

    public void setCompetition(CompetitionEntity competition) {
        this.competition = competition;
    }
}
