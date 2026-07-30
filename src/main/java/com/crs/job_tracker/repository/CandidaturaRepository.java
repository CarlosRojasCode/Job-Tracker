package com.crs.job_tracker.repository;

import com.crs.job_tracker.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

// al extender JpaRepository, heredamos métodos ya implementados:
// save(), findById(), findAll(), deleteById(), count(), etc.
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}