package com.skilldistillery.tracker.entities;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Session {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String Title;

	public Session() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Session(int id, String title) {
		super();
		this.id = id;
		Title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Title, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(Title, other.Title) && id == other.id;
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", Title=" + Title + "]";
	}

}
