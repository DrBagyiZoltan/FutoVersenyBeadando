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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
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
        return new RedirectView("/runners");
    }

    @GetMapping("/getRaceRunners/{id}")
    public RedirectView getAllResultOfCompetition(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        List<ResultEntity> resultEntities = resultRepository.findAll();

        List<ResultEntity> filteredAndSortedResults = resultEntities.stream()
                .filter(result -> result.getCompetitionEntity().getCompetitionId() == id)
                .sorted(Comparator.comparingDouble(ResultEntity::getTimeResult))
                .toList();

        redirectAttributes.addAttribute(getAverageTime(id));
        redirectAttributes.addAttribute(filteredAndSortedResults);
        return new RedirectView("/detailedCompetitionView");
    }

    @GetMapping("/getRaces")
    public List<CompetitionEntity> getRaceRunners() {
        return competitionRepository.findAll();
    }

    @PostMapping("/updateRace/{id}")
    public RedirectView updateRace(@PathVariable Long id,
                                   @RequestParam("competitionName") String competitionName,
                                   @RequestParam("distance") Float newDistance) {
        CompetitionEntity competitionToModify = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        competitionToModify.setCompetitionName(competitionName);
        competitionToModify.setDistance(newDistance);
        competitionRepository.save(competitionToModify);
        return new RedirectView("/competitions");
    }

    @GetMapping("/getAverageTime/{resultId}")
    public double getAverageTime(@PathVariable Long id) {
        List<ResultEntity> resultEntities = resultRepository.findAll();
        List<ResultEntity> filteredAndSortedResults = resultEntities.stream()
                .filter(result -> result.getCompetitionEntity().getCompetitionId() == id)
                .sorted(Comparator.comparingDouble(ResultEntity::getTimeResult))
                .toList();
        return filteredAndSortedResults.stream()
                .mapToDouble(ResultEntity::getTimeResult)
                .average().orElse(0.0);
    }

    @PostMapping("/addResult/{id}")
    public RedirectView addResult(@PathVariable Long id,
                          @RequestParam(name="isNewRunner", defaultValue = "false") boolean isNewRunner,
                          @RequestParam("name") String runnerName,
                          @RequestParam("result") Float result) {
        CompetitionEntity competition = competitionRepository.findById(id).orElseThrow();
        if (!isNewRunner) {
            List<RunnerEntity> runners = runnerRepository.findAll();
            RunnerEntity runner = runners.stream()
                    .filter(r -> r.getRunnerName().equals(runnerName))
                    .findFirst().orElseThrow();
            ResultEntity newResult = new ResultEntity(result, competition, runner);
            resultRepository.save(newResult);
            return new RedirectView("/competitions");
        }
        return new RedirectView("/runners");
    }
}