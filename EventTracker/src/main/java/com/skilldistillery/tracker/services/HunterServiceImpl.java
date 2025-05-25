package com.skilldistillery.tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Hunter;
import com.skilldistillery.tracker.repositories.HunterRepository;

@Service
public class HunterServiceImpl implements HunterService {

	@Autowired
	private HunterRepository hunterRepo;

	@Override
	public Hunter findById(int id) {
		return hunterRepo.findById(id).orElse(null);
	}

}
