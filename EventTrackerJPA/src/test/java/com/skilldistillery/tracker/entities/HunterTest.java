package com.skilldistillery.tracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class HunterTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Hunter hunter;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("EventTrackerJPA");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em= emf.createEntityManager();
		hunter = em.find(Hunter.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		hunter = null;
	}


	@Test
	void test_Hunter_entity_db_mapping() {
		assertNotNull(hunter.getId());
		assertEquals(1, hunter.getId());
		assertEquals("Hog Durkins", hunter.getName());
	}
	
	@Test
	void test_Hunter_OneToMany_returns_not_empty_list_of_bounties() {
		assertNotNull(hunter.getBounties());
	}

}
