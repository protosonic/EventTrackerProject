package com.skilldistillery.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tracker.entities.Target;

public interface TargetRepository extends JpaRepository<Target, Integer> {

}
