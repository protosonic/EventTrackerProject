package com.skilldistillery.tracker.services;

import java.util.List;

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


	

}
