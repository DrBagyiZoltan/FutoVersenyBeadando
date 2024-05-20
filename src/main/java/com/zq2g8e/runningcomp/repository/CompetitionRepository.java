package com.zq2g8e.runningcomp.repository;

import com.zq2g8e.runningcomp.entity.CompetitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<CompetitionEntity,Long > {
}