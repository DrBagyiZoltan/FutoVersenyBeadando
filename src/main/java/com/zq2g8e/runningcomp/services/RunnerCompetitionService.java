package com.zq2g8e.runningcomp.services;

import com.zq2g8e.runningcomp.repository.CompetitionRepository;
import com.zq2g8e.runningcomp.repository.ResultRepository;
import com.zq2g8e.runningcomp.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class RunnerCompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;
    private RunnerRepository runnerRepository;
    private ResultRepository resultRepository;
}
