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

import com.skilldistillery.tracker.entities.Target;
import com.skilldistillery.tracker.services.TargetService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api")
public class TargetController {

	@Autowired
	TargetService targetService;

	@GetMapping("targets")
	public List<Target> index() {
		return targetService.findAll();
	}

	@GetMapping("targets/{targetId}")
	public Target findById(@PathVariable("targetId") int targetId, HttpServletResponse res) {
		Target target = targetService.findById(targetId);
		if (target == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return target;
	}

	@PostMapping("targets")
	public Target create(@RequestBody Target newTarget, HttpServletResponse res, HttpServletRequest req) {
		try {
			targetService.create(newTarget);
			res.setStatus(HttpServletResponse.SC_CREATED); // 201
			res.setHeader("location", req.getRequestURL().append("/").append(newTarget.getId()).toString());
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400
			newTarget = null;
		}
		return newTarget;
	}

	@PutMapping("targets/{targetId}")
	public Target update(@RequestBody Target target, @PathVariable("targetId") int targetId, HttpServletResponse res) {
		Target updatedTarget = null;
		try {
			updatedTarget = targetService.update(targetId, target);
			if (updatedTarget == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400

		}
		return updatedTarget;
	}

	@DeleteMapping("targets/{targetId}")
	public void delete(@PathVariable("targetId") int targetId, HttpServletResponse res) {
		try {
			if (!targetService.delete(targetId)) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); //404
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); //400
		}
	}
}
