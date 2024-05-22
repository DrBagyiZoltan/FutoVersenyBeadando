package com.zq2g8e.runningcomp.controller;

import com.zq2g8e.runningcomp.entity.CompetitionEntity;
import com.zq2g8e.runningcomp.entity.ResultEntity;
import com.zq2g8e.runningcomp.entity.RunnerEntity;
import com.zq2g8e.runningcomp.repository.CompetitionRepository;
import com.zq2g8e.runningcomp.repository.ResultRepository;
import com.zq2g8e.runningcomp.repository.RunnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping("/{id}")
    public RunnerEntity getRunner(@PathVariable Long id) {
        return runnerRepository.findById(id).orElse(null);
    }

    @GetMapping("/getRunners")
    public List<RunnerEntity> getAllRunners() {
        return runnerRepository.findAll();
    }

    @PostMapping("/addRunner")
    public RedirectView addRunner(@RequestParam("name") String runnerName,
                                  @RequestParam("age") String runnerAge,
                                  @RequestParam("sex") String sex) {
        RunnerEntity newRunner = new RunnerEntity(runnerName, Integer.valueOf(runnerAge), sex);
        runnerRepository.save(newRunner);
        //return "redirect:/getRunners";
        return new RedirectView("/runners");
    }


    //@GetMapping("/getRaceRunners/{competitionId}")
    @GetMapping("/getRaces")
    //public List<CompetitionEntity> getRaceRunners(@PathVariable Long id) {
    public List<ResultEntity> getRaceRunners() {
        return resultRepository.findAll();
        //return competitionRepository.findById(id).orElse(null);
    }

    /*@PutMapping("/updateRace")
    public CompetitionEntity updateCompetition(@PathVariable Long id, @RequestBody String name) {
        CompetitionEntity competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        competition.setCompetitionName(competition.getCompetitionName());
        competition.setDistance(competition.getDistance());

        return competitionRepository.save(competition);
    }*/
    @PostMapping("/updateRace/{id}")
    public RedirectView updateRace(@PathVariable Long id,
                                   @RequestParam("competitionName") String competitionName,
                                   @RequestParam("distance") Float newDistance) {
        CompetitionEntity competitionToModify = competitionRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        competitionToModify.setCompetitionName(competitionName);
        competitionToModify.setDistance(newDistance);
        competitionRepository.save(competitionToModify);
        return new RedirectView("/competitions");
    }
    /*@PutMapping("/updateRace")
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
    */
}