package com.skilldistillery.tracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.tracker.entities.Bounty;

public interface BountyRepository extends JpaRepository<Bounty, Integer> {
	List<Bounty> findByHunter_Id(int hunterId); 
}
