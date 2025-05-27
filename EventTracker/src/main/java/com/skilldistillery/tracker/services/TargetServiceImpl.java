package com.skilldistillery.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Target;
import com.skilldistillery.tracker.repositories.TargetRepository;

@Service
public class TargetServiceImpl implements TargetService {
	
	@Autowired
	private TargetRepository targetRepo;
	
	@Override
	public List<Target> findAll() {
		return targetRepo.findAll();
	}
	@Override
	public Target findById(int id) {
		return targetRepo.findById(id).orElse(null);
	}
	@Override
	public Target create(Target target) {
		if (target.getWantedLevel() == null)
			target.setWantedLevel(1);
		return targetRepo.saveAndFlush(target);
	}
	@Override
	public Target update(int id, Target target) {
		
		Optional<Target> targetOpt = targetRepo.findById(id);
		
		Target managedTarget = null;
		
		if(targetOpt.isPresent()) {
			managedTarget = targetOpt.get();
			managedTarget.setName(target.getName());
			managedTarget.setSpecies(target.getSpecies());
			managedTarget.setWantedLevel(target.getWantedLevel());
			managedTarget.setPlanetName(target.getPlanetName());
			managedTarget.setLastSeen(target.getLastSeen());
			managedTarget.setImageUrl(target.getImageUrl());
			
			targetRepo.saveAndFlush(managedTarget);
		}
		return managedTarget;
	}
	@Override
	public boolean delete(int id) {
		if(targetRepo.existsById(id)) {
			targetRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
