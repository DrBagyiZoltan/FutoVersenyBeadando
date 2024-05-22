package com.zq2g8e.runningcomp.repository;

import com.zq2g8e.runningcomp.entity.RunnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RunnerRepository extends JpaRepository<RunnerEntity,Long > {
}