package com.skilldistillery.tracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class TargetTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Target target;

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
		target = em.find(Target.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		target = null;
	}

	@Test
	void test_Target_entity_db_mapping() {
		assertNotNull(target.getId());
		assertEquals(1, target.getId());
		assertEquals("Lucarius Sylvinate", target.getName());
	}
	
	@Test
	void test_Target_OneToMany_returns_not_empty_list_of_bounties() {
		assertNotNull(target.getBountiesOnTarget());
	}

}
