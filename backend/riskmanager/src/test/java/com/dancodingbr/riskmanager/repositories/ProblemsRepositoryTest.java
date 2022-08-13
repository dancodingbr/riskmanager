package com.dancodingbr.riskmanager.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dancodingbr.riskmanager.models.Problem;

@DataJpaTest
public class ProblemsRepositoryTest {

	@Autowired
	private ProblemsRepository problemsRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void it_should_returns_all_problems_list_when_finds_all() {

		// arrange
		List<Problem> expectedProblemsList = Arrays.asList(
				new Problem(null, "BAD GRADES ON MATH"),
				new Problem(null, "BODY WEIGHT RAISING"));
		
		for (Problem problem : expectedProblemsList) {
			entityManager.persistAndFlush(problem);
		}
		
		// act
		List<Problem> actualProblemsList = this.problemsRepository.findAll();
		
		// assert
		assertEquals(expectedProblemsList, actualProblemsList);
	}
}
