package com.skilldistillery.tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.tracker.entities.Bounty;
import com.skilldistillery.tracker.repositories.BountyRepository;
import com.skilldistillery.tracker.repositories.HunterRepository;
import com.skilldistillery.tracker.repositories.TargetRepository;

@Service
public class BountyServiceImpl implements BountyService {

	@Autowired
	private BountyRepository bountyRepo;

	@Autowired
	private HunterRepository hunterRepo;

	@Autowired
	private TargetRepository targetRepo;

	@Override
	public List<Bounty> findAll() {
		return bountyRepo.findAll();
	}

	@Override
	public Bounty findById(int id) {
		return bountyRepo.findById(id).orElse(null);
	}

	@Override
	public Bounty create(Bounty bounty) {
		return bountyRepo.saveAndFlush(bounty);
	}

	@Override
	public Bounty update(int id, Bounty bounty) {
		Optional<Bounty> bountyOpt = bountyRepo.findById(id);
		Bounty managedBounty = null;
		if (bountyOpt.isPresent()) {
			managedBounty = bountyOpt.get();
			managedBounty.setAmount(bounty.getAmount());
			managedBounty.setStatus(bounty.isStatus());
			managedBounty.setDescription(bounty.getDescription());
			managedBounty.setDangerLevel(bounty.getDangerLevel());
			managedBounty.setIssueDate(bounty.getIssueDate());
			managedBounty.setClaimedDate(bounty.getClaimedDate());
			if (managedBounty.getHunter() != null && bounty.getHunter().getId() > 0) {
				managedBounty.setHunter(hunterRepo.findById(bounty.getHunter().getId()).get());
			}
			if (managedBounty.getTarget() != null && bounty.getTarget().getId() > 0) {
				managedBounty.setTarget(targetRepo.findById(bounty.getTarget().getId()).get());
			}
			managedBounty.setImageUrl(bounty.getImageUrl());

			bountyRepo.saveAndFlush(managedBounty);

		}
		return managedBounty;
	}

	@Override
	public boolean delete(int id) {
		if (bountyRepo.existsById(id)) {
			bountyRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<Bounty> findByHunterId(int hunterId) {
	    return bountyRepo.findByHunter_Id(hunterId);
	}

	
}
