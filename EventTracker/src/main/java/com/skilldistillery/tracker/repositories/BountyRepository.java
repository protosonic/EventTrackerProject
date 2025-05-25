package com.skilldistillery.tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tracker.entities.Bounty;

public interface BountyRepository extends JpaRepository<Bounty, Integer> {

}
