package com.skilldistillery.tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.tracker.entities.Hunter;
import com.skilldistillery.tracker.services.HunterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class HunterController {

	@Autowired
	private HunterService hunterService;

	@GetMapping("hunters")
	public List<Hunter> index() {
		return hunterService.findAll();
	}

	@GetMapping("hunters/{hunterId}")
	public Hunter findById(@PathVariable("hunterId") int hunterId, HttpServletResponse res) {
		Hunter hunter = hunterService.findById(hunterId);
		if (hunter == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return hunter;
	}

	@PostMapping("hunters")
	public Hunter create(@RequestBody Hunter newHunter, HttpServletResponse res, HttpServletRequest req) {
		try {
			hunterService.create(newHunter);
			res.setStatus(HttpServletResponse.SC_CREATED); // 201
			res.setHeader("location", req.getRequestURL().append("/").append(newHunter.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
			newHunter = null;
		}
		return newHunter;
	}
	
	@PutMapping("hunters/{hunterId}")
	public Hunter update(@RequestBody Hunter hunter, @PathVariable("hunterId") int hunterId, HttpServletResponse res) {
		Hunter updatedHunter = null;
		
		try {
			updatedHunter = hunterService.update(hunterId, hunter);
			if(updatedHunter == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
		}
		return updatedHunter;
	}
	
	@DeleteMapping("hunters/{hunterId}")
	public void delete(@PathVariable("hunterId") int hunterId, HttpServletResponse res) {
		try {
			if(!hunterService.delete(hunterId)) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}
