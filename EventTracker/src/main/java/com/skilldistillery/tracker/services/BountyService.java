package com.skilldistillery.tracker.services;

import java.util.List;

import com.skilldistillery.tracker.entities.Bounty;

public interface BountyService {
	List<Bounty> findAll();

	Bounty findById(int id);

	Bounty create(Bounty bounty);

	Bounty update(int id, Bounty bounty);

	boolean delete(int id);
	
	List<Bounty> findByHunterId(int hunterId);
}
