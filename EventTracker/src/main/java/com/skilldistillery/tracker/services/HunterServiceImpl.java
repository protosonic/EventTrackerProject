package com.skilldistillery.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Hunter;
import com.skilldistillery.tracker.repositories.HunterRepository;

@Service
public class HunterServiceImpl implements HunterService {

	@Autowired
	private HunterRepository hunterRepo;
	
	@Override
	public List<Hunter> findAll() {
		return hunterRepo.findAll();
	}

	@Override
	public Hunter findById(int id) {
		return hunterRepo.findById(id).orElse(null);
	}

	@Override
	public Hunter create(Hunter hunter) {
		if(hunter.getRank() == null) {
			hunter.setRank("N008");
		}
		return hunterRepo.saveAndFlush(hunter);
	}

	@Override
	public Hunter update(int id, Hunter hunter) {
		Optional<Hunter> hunterOpt = hunterRepo.findById(id);
		Hunter managedHunter = null;
		if(hunterOpt.isPresent()) {
			managedHunter = hunterOpt.get();
			managedHunter.setName(hunter.getName());
			managedHunter.setRank(hunter.getRank());
			managedHunter.setReputation(hunter.getReputation());
			managedHunter.setImageUrl(hunter.getImageUrl());
			
			hunterRepo.saveAndFlush(managedHunter);
		}
		return managedHunter;
	}

	@Override
	public boolean delete(int id) {
		if(hunterRepo.existsById(id)) {
			hunterRepo.deleteById(id);
			return true;
		}
		return false;
	}

}
