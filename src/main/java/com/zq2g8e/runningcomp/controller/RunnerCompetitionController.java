package com.zq2g8e.runningcomp.controller;

import com.zq2g8e.runningcomp.entity.CompetitionEntity;
import com.zq2g8e.runningcomp.entity.ResultEntity;
import com.zq2g8e.runningcomp.entity.RunnerEntity;
import com.zq2g8e.runningcomp.entity.Sex;
import com.zq2g8e.runningcomp.repository.CompetitionRepository;
import com.zq2g8e.runningcomp.repository.ResultRepository;
import com.zq2g8e.runningcomp.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;


@Controller
public class RunnerCompetitionController {

    @Autowired
    private RunnerRepository runnerRepository;

    @Autowired
    private CompetitionRepository competitionRepository;

    @Autowired
    private ResultRepository resultRepository;

    @GetMapping("/runners")
    public String getAllRunners(Model model) {
        List<RunnerEntity> runners = runnerRepository.findAll();
        model.addAttribute("runners", runners);
        return "list";
    }

    @GetMapping("/competitions")
    public String getAllRaces(Model model) {
        List<CompetitionEntity> competitions = competitionRepository.findAll();
        model.addAttribute("competitions", competitions);
        return "races";
    }

    @PostMapping("/addRace")
    public String addRace(@RequestParam("competitionName") String competitionName, @RequestParam("distance") Float distance) {
        CompetitionEntity newCompetition = new CompetitionEntity(competitionName, distance);
        competitionRepository.save(newCompetition);
        return "redirect:/competitions";
    }

    @PostMapping("/competitions/{id}")
    public String updateCompetition(@PathVariable("id") long id, CompetitionEntity competition, Model model) {
        competitionRepository.save(competition);
        return "redirect:/competitions";
    }

    @GetMapping("/detailedCompetitionView")
    public String getAllResultOfCompetition(
            @RequestParam("resultEntityList") List<ResultEntity> filteredAndSortedResults,
            @RequestParam("double") double averageTime,
            Model model) {
        CompetitionEntity competition = filteredAndSortedResults.get(0).getCompetitionEntity();
        model.addAttribute("filteredAndSortedResults", filteredAndSortedResults);
        model.addAttribute("competition", competition);
        model.addAttribute("averageTime", averageTime);
        return "details";
    }

    @GetMapping("update/{id}")
    public String showToUpdate(@PathVariable long id, RedirectAttributes redirectAttributes) {
        CompetitionEntity competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        //redirectAttributes.addAttribute("competitionId", id);
        redirectAttributes.addAttribute(id);
        //model.addAttribute("competition",competition);
        //return new ModelAndView("redirect:/update/{id}", model);
        return "redirect:/update";
    }

    @GetMapping("/update")
    public String getUpdatePage(@RequestParam("long") Long id, Model model) {
        CompetitionEntity competition = competitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        model.addAttribute("competition", competition);
        return "update";
    }

    /*@PostMapping("/calculate")
    public String getAverageTime(Model model, @RequestParam("resultId") Long resultId) {
       *//* String url = "http://localhost:8080/api/v1/runnercompetition/getAverageTime/" + resultId;
        RestTemplate restTemplate = null;
        String averageTime = restTemplate.getForObject(url, String.class);*//*
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String url = "http://localhost:8080/api/v1/runnercompetition/getAverageTime/" + resultId;
            HttpGet request = new HttpGet(url);

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String responseBody = EntityUtils.toString(entity);
                    model.addAttribute("averageTime", responseBody);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle error
            model.addAttribute("averageTime", "Error");
        }

        return "details";
    }*/

}

