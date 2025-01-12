package com.zq2g8e.runningcomp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "runner")
public class RunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long runnerId;
    private String runnerName;
    private Integer runnerAge;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public RunnerEntity() {
    }

    public RunnerEntity(String runnerName) {
        this.runnerName = runnerName;
    }

    public RunnerEntity(String runnerName, Integer runnerAge, String sex) {
        this.runnerName = runnerName;
        this.runnerAge = runnerAge;
        this.sex = Sex.valueOf(sex.toUpperCase());
    }

    public long getRunnerId() {
        return runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public Integer getRunnerAge() {
        return runnerAge;
    }

    public void modifyRunnerAge(Integer newRunnerAge) {
        this.runnerAge = newRunnerAge;
    }

    public Sex getSex() {
        return sex;
    }
}
