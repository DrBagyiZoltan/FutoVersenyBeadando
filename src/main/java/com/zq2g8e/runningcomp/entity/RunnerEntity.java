package com.zq2g8e.runningcomp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "runner")
public class RunnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long runnerId;
    private String runnerName;
    private int runnerAge;
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public RunnerEntity() {
    }

    public RunnerEntity(String runnerName, int runnerAge, Sex sex) {
        this.runnerName = runnerName;
        this.runnerAge = runnerAge;
        this.sex = sex;
    }

    public long getRunnerId() {
        return runnerId;
    }

    public String getRunnerName() {
        return runnerName;
    }

    public int getRunnerAge() {
        return runnerAge;
    }

    public void modifyRunnerAge(int newRunnerAge) {
        this.runnerAge = newRunnerAge;
    }

    public Sex getSex() {
        return sex;
    }
}
