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
public class Target {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String species;
	@Column(name = "wanted_level")
	private Integer wantedLevel;
	@Column(name = "planet_name")
	private String planetName;
	@Column(name = "last_seen")
	private String lastSeen;
	@Column(name = "image_url")
	private String imageUrl;
	@OneToMany(mappedBy = "target")
	@JsonIgnore
	private List<Bounty> bountiesOnTarget;

	public Target() {
		super();
	}

	public Target(int id, String name, String species, Integer wantedLevel, String planetName, String lastSeen,
			String imageUrl, List<Bounty> bountiesOnTarget) {
		super();
		this.id = id;
		this.name = name;
		this.species = species; 
		this.planetName = planetName;
		this.lastSeen = lastSeen;
		this.imageUrl = imageUrl;
		this.bountiesOnTarget = bountiesOnTarget;
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

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Integer getWantedLevel() {
		return wantedLevel;
	}

	public void setWantedLevel(Integer wantedLevel) {
		this.wantedLevel = wantedLevel;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<Bounty> getBountiesOnTarget() {
		return bountiesOnTarget;
	}

	public void setBountiesOnTarget(List<Bounty> bountiesOnTarget) {
		this.bountiesOnTarget = bountiesOnTarget;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bountiesOnTarget, id, imageUrl, lastSeen, name, planetName, species, wantedLevel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Target other = (Target) obj;
		return Objects.equals(bountiesOnTarget, other.bountiesOnTarget) && id == other.id
				&& Objects.equals(imageUrl, other.imageUrl) && Objects.equals(lastSeen, other.lastSeen)
				&& Objects.equals(name, other.name) && Objects.equals(planetName, other.planetName)
				&& Objects.equals(species, other.species) && Objects.equals(wantedLevel, other.wantedLevel);
	}

	@Override
	public String toString() {
		return "Target [id=" + id + ", name=" + name + ", species=" + species + ", wantedLevel=" + wantedLevel
				+ ", planetName=" + planetName + ", lastSeen=" + lastSeen + ", imageUrl=" + imageUrl
				+ ", bountiesOnTarget=" + bountiesOnTarget + "]";
	}

}
