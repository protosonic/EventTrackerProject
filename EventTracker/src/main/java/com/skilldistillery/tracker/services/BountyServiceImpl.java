package com.skilldistillery.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Bounty;
import com.skilldistillery.tracker.repositories.BountyRepository;

@Service
public class BountyServiceImpl implements BountyService {

	@Autowired
	private BountyRepository bountyRepo;

	@Override
	public Bounty findById(int id) {
		return bountyRepo.findById(id).orElse(null);
	}

}
