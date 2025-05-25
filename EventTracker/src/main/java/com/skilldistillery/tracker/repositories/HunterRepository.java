package com.skilldistillery.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tracker.entities.Hunter;

public interface HunterRepository extends JpaRepository<Hunter, Integer> {

}
