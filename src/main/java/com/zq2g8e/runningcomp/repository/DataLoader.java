package com.zq2g8e.runningcomp.repository;
import com.zq2g8e.runningcomp.entity.CompetitionEntity;
import com.zq2g8e.runningcomp.entity.ResultEntity;
import com.zq2g8e.runningcomp.entity.RunnerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class DataLoader implements CommandLineRunner {

    private final RunnerRepository runnerRepository;
    private final CompetitionRepository competitionRepository;
    private final ResultRepository resultRepository;

    @Autowired
    public DataLoader(RunnerRepository runnerRepository,
                      CompetitionRepository competitionRepository,
                      ResultRepository resultRepository) {
        this.runnerRepository = runnerRepository;
        this.resultRepository = resultRepository;
        this.competitionRepository = competitionRepository;
    }

    @Override
    public void run(String... args) {

        RunnerEntity runnerEntity1 = new RunnerEntity("Geza", 20, "male");
        RunnerEntity runnerEntity2 = new RunnerEntity("Laci",21, "male");
        RunnerEntity runnerEntity3 = new RunnerEntity("Teri",18, "female");
        RunnerEntity runnerEntity4 = new RunnerEntity("Kati", 21, "female");

        runnerRepository.save(runnerEntity1);
        runnerRepository.save(runnerEntity2);
        runnerRepository.save(runnerEntity3);
        runnerRepository.save(runnerEntity4);

        CompetitionEntity competitionEntity1 = new CompetitionEntity("100m", 100);
        CompetitionEntity competitionEntity2 = new CompetitionEntity("Marathon",42);
        competitionRepository.save(competitionEntity1);
        competitionRepository.save(competitionEntity2);

        ResultEntity resultEntity1 = new ResultEntity(0.5f, competitionEntity1, runnerEntity1);
        ResultEntity resultEntity2 = new ResultEntity(0.56f, competitionEntity1, runnerEntity2);
        ResultEntity resultEntity3 = new ResultEntity(296.6f, competitionEntity2, runnerEntity3);
        ResultEntity resultEntity4 = new ResultEntity(300.2f, competitionEntity2, runnerEntity4);

        resultRepository.save(resultEntity1);
        resultRepository.save(resultEntity2);
        resultRepository.save(resultEntity3);
        resultRepository.save(resultEntity4);
    }
}