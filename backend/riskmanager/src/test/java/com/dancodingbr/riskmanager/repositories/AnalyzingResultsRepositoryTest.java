package com.dancodingbr.riskmanager.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dancodingbr.riskmanager.models.AnalyzedResult;

@DataJpaTest
public class AnalyzingResultsRepositoryTest {
	
	@Autowired
	private AnalyzedResultRepository analyzedResultRepository;
	
	@Autowired
    private TestEntityManager entityManager;	

	@Test
	public void it_should_returns_analyzed_result_when_finds_by_problem_and_action_plan_and_probability_level_and_impact_level() {

		// arrange
		String problem = "Bad grades on math";
		String actionPlan = "Study 8 hours per week on next semester";
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		
		AnalyzedResult expected = new AnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel);
		entityManager.persistAndFlush(expected);
		
		// act
		AnalyzedResult actual = this.analyzedResultRepository.findByProblemAndActionPlanAndProbabilityLevelAndImpactLevel(problem, actionPlan, probabilityLevel, impactLevel);
		
		// assert
		assertEquals(problem, actual.getProblem());
		assertEquals(actionPlan, actual.getActionPlan());
		assertEquals(probabilityLevel, actual.getProbabilityLevel());
		assertEquals(impactLevel, actual.getImpactLevel());
	}
	
}
