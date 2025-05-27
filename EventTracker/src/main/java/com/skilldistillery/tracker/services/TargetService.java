package com.skilldistillery.tracker.services;

import java.util.List;

import com.skilldistillery.tracker.entities.Target;

public interface TargetService {
	List<Target> findAll();

	Target findById(int id);

	Target create(Target target);

	Target update(int id, Target target);

	boolean delete(int id);
}
