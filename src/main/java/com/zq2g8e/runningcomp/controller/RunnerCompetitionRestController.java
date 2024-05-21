package com.zq2g8e.runningcomp.controller;

import com.zq2g8e.runningcomp.entity.CompetitionEntity;
import com.zq2g8e.runningcomp.entity.ResultEntity;
import com.zq2g8e.runningcomp.entity.RunnerEntity;
import com.zq2g8e.runningcomp.repository.CompetitionRepository;
import com.zq2g8e.runningcomp.repository.ResultRepository;
import com.zq2g8e.runningcomp.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/runnercompetition")
public class RunnerCompetitionRestController {


    @Autowired
    private RunnerRepository runnerRepository;
    private CompetitionRepository competitionRepository;
    private ResultRepository resultRepository;

    public RunnerCompetitionRestController(
            RunnerRepository runnerRepository,
            CompetitionRepository competitionRepository,
            ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.competitionRepository = competitionRepository;
        this.resultRepository = resultRepository;
    }

    @GetMapping("/getRunners")
    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    @PostMapping("/addRunner")
    @ResponseStatus(HttpStatus.OK)
    public void addRunner(@RequestBody RunnerEntity runner) {
        runnerRepository.save(runner);
    }

    //@GetMapping("/getRaceRunners/{competitionId}")
    @GetMapping("/getRaces")
    //public List<CompetitionEntity> getRaceRunners(@PathVariable Long id) {
    public List<ResultEntity> getRaceRunners() {
        return resultRepository.findAll();
        //return competitionRepository.findById(id).orElse(null);
    }

    @PostMapping("/updateRace")
    @ResponseStatus(HttpStatus.OK)
    public void updateRace(@RequestBody CompetitionEntity competition) {
        competitionRepository.save(competition);
    }

    @PostMapping("/addResult")
    @ResponseStatus(HttpStatus.OK)
    public void addResult(@RequestBody ResultEntity result) {
        resultRepository.save(result);
    }

    @GetMapping("/getAverageTime/{competitionId}")
    public CompetitionEntity getAverageTime(@PathVariable Long id) {
        return competitionRepository.findById(id).orElse(null);
    }
}