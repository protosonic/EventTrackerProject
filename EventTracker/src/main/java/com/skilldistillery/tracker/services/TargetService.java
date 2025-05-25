package com.skilldistillery.tracker.services;

import java.util.List;

import com.skilldistillery.tracker.entities.Target;

public interface TargetService {
	List<Target> findAll();
	Target findById(int id);
}
