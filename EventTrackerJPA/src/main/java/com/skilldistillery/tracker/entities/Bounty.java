package com.skilldistillery.tracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bounty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int amount;
	private boolean status;
	private String description;
	@Column(name = "danger_level")
	private int dangerLevel;
	@Column(name = "issue_date")
	private LocalDateTime issueDate;
	@Column(name = "claimed_date")
	private LocalDateTime claimedDate;
	@ManyToOne
	@JoinColumn(name = "hunter_id")
	private Hunter hunter;
	@ManyToOne
	@JoinColumn(name = "target_id")
	private Target target;
	@Column(name = "image_url")
	private String imageUrl;

	public Bounty() {
		super();
	}

	public Bounty(int id, int amount, boolean status, String description, int dangerLevel, LocalDateTime issueDate,
			LocalDateTime claimedDate, Hunter hunter, Target target, String imageUrl) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.dangerLevel = dangerLevel;
		this.issueDate = issueDate;
		this.claimedDate = claimedDate;
		this.hunter = hunter;
		this.target = target;
		this.imageUrl = imageUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public LocalDateTime getClaimedDate() {
		return claimedDate;
	}

	public void setClaimedDate(LocalDateTime claimedDate) {
		this.claimedDate = claimedDate;
	}

	public Hunter getHunter() {
		return hunter;
	}

	public void setHunter(Hunter hunter) {
		this.hunter = hunter;
	}

	public Target getTarget() {
		return target;
	}

	public void setTarget(Target target) {
		this.target = target;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, claimedDate, dangerLevel, description, hunter, id, imageUrl, issueDate, status,
				target);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bounty other = (Bounty) obj;
		return amount == other.amount && Objects.equals(claimedDate, other.claimedDate)
				&& dangerLevel == other.dangerLevel && Objects.equals(description, other.description)
				&& Objects.equals(hunter, other.hunter) && id == other.id && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(issueDate, other.issueDate) && status == other.status
				&& Objects.equals(target, other.target);
	}

	@Override
	public String toString() {
		return "Bounty [id=" + id + ", amount=" + amount + ", status=" + status + ", description=" + description
				+ ", dangerLevel=" + dangerLevel + ", issueDate=" + issueDate + ", claimedDate=" + claimedDate
				+ ", hunter=" + hunter + ", target=" + target + ", imageUrl=" + imageUrl + "]";
	}

}
