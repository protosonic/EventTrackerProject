package com.skilldistillery.tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Target;
import com.skilldistillery.tracker.services.TargetService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class TargetController {
	
	@Autowired
	TargetService targetService;
	
	@GetMapping("targets/{targetId}")
	public Target findById(@PathVariable("targetId") int targetId, HttpServletResponse res) {
		Target target = targetService.findById(targetId);
		if(target == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return target;
	}
	
}
