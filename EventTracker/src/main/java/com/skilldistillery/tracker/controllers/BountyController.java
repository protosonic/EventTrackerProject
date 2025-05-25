package com.skilldistillery.tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Bounty;
import com.skilldistillery.tracker.services.BountyService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class BountyController {

	@Autowired
	private BountyService bountyService;

	@GetMapping("bounties/{bountyId}")
	public Bounty findById(@PathVariable("bountyId") int bountyd, HttpServletResponse res) {
		Bounty bounty = bountyService.findById(bountyd);
		if (bounty == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return bounty;
	}

}
