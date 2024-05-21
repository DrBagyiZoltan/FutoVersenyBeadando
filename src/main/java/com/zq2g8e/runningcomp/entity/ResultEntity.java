package com.zq2g8e.runningcomp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "results")
public class ResultEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long resultId;

    @ManyToOne
    @JoinColumn(name = "runner_id", nullable = false)
    private RunnerEntity runnerEntity;

    private float timeResult;

    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private CompetitionEntity competitionEntity;

    public ResultEntity() {
    }

    public ResultEntity(float timeResult, CompetitionEntity competitionEntity, RunnerEntity runnerEntity) {
        this.timeResult = timeResult;
        this.competitionEntity = competitionEntity;
        this.runnerEntity = runnerEntity;
    }

    public long getResultId() {
        return resultId;
    }

    public float getTimeResult() {
        return timeResult;
    }

    public RunnerEntity getRunnerEntity() {
        return runnerEntity;
    }

    public CompetitionEntity getCompetitionEntity() {
        return competitionEntity;
    }
}
