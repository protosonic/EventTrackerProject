package com.skilldistillery.tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Bounty;
import com.skilldistillery.tracker.services.BountyService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class BountyController {

	@Autowired
	private BountyService bountyService;
	
	@GetMapping("bounties")
	public List<Bounty> index() {
		return bountyService.findAll();
	}

	@GetMapping("bounties/{bountyId}")
	public Bounty findById(@PathVariable("bountyId") int bountyd, HttpServletResponse res) {
		Bounty bounty = bountyService.findById(bountyd);
		if (bounty == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return bounty;
	}
	@PostMapping("bounties")
	public Bounty create(@RequestBody Bounty newBounty, HttpServletResponse res, HttpServletRequest req) {
		try {
			bountyService.create(newBounty);
			res.setStatus(HttpServletResponse.SC_CREATED); // 201
			res.setHeader("location", req.getRequestURL().append("/").append(newBounty.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
			newBounty = null;
		}
		return newBounty;
	}
	
	@PutMapping("bounties/{bountyId}")
	public Bounty update(@RequestBody Bounty bounty, @PathVariable("bountyId") int bountyId, HttpServletResponse res) {
		Bounty updatedBounty = null;
		
		try {
			updatedBounty = bountyService.update(bountyId, bounty);
			if(updatedBounty == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
		}
		return updatedBounty;
	}
	
	@DeleteMapping("bounties/{bountyId}")
	public void delete(@PathVariable("bountyId") int bountyId, HttpServletResponse res) {
		try {
			if(!bountyService.delete(bountyId)) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
		}
	}
	
	@GetMapping("/hunters/{hunterId}/bounties")
	public List<Bounty> getBountiesByHunterId(@PathVariable("hunterId") int hunterId) {
	    return bountyService.findByHunterId(hunterId);
	}

	
}
