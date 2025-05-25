package com.skilldistillery.tracker.entities;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Hunter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String rank;
	private int reputaion;
	@Column(name = "image_url")
	private String imageUrl;
	@OneToMany(mappedBy = "hunter")
	@JsonIgnore
	private List<Bounty> bounties;

	public Hunter() {
		super();
	}

	public Hunter(int id, String name, String rank, int reputaion, String imageUrl, List<Bounty> bounties) {
		super();
		this.id = id;
		this.name = name;
		this.rank = rank;
		this.reputaion = reputaion;
		this.imageUrl = imageUrl;
		this.bounties = bounties;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public int getReputaion() {
		return reputaion;
	}

	public void setReputaion(int reputaion) {
		this.reputaion = reputaion;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Bounty> getBounties() {
		return bounties;
	}

	public void setBounties(List<Bounty> bounties) {
		this.bounties = bounties;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bounties, id, imageUrl, name, rank, reputaion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hunter other = (Hunter) obj;
		return Objects.equals(bounties, other.bounties) && id == other.id && Objects.equals(imageUrl, other.imageUrl)
				&& Objects.equals(name, other.name) && Objects.equals(rank, other.rank) && reputaion == other.reputaion;
	}

	@Override
	public String toString() {
		return "Hunter [id=" + id + ", name=" + name + ", rank=" + rank + ", reputaion=" + reputaion + ", imageUrl="
				+ imageUrl + ", bounties=" + bounties + "]";
	}

}
