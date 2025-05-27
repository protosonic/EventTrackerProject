package com.skilldistillery.tracker.services;

import java.util.List;

import com.skilldistillery.tracker.entities.Hunter;

public interface HunterService {
	List<Hunter> findAll();

	Hunter findById(int id);

	Hunter create(Hunter hunter);

	Hunter update(int id, Hunter hunter);

	boolean delete(int id);
}
