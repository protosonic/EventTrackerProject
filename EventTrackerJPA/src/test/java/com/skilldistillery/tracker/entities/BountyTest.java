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

class BountyTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Bounty bounty;

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
		bounty = em.find(Bounty.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		bounty = null;
	}

	@Test
	void test_Bounty_entity_db_mapping() {
		assertNotNull(bounty.getId());
		assertEquals(1, bounty.getId());
		assertEquals(50000, bounty.getAmount());
	}

}
