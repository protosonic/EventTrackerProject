package com.skilldistillery.tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Hunter;
import com.skilldistillery.tracker.services.HunterService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class HunterController {

	@Autowired
	private HunterService hunterService;

	@GetMapping("hunters/{hunterId}")
	public Hunter findById(@PathVariable("hunterId") int hunterId, HttpServletResponse res) {
		Hunter hunter = hunterService.findById(hunterId);
		if (hunter == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return hunter;
	}

}
