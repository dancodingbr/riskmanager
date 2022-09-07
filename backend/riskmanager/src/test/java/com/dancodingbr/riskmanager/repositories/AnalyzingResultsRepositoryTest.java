package com.dancodingbr.riskmanager.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;



import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskAssessmentMatrix;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.models.Problem;

@DataJpaTest
public class AnalyzingResultsRepositoryTest {
	
	@Autowired
	private AnalyzedResultRepository analyzedResultRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void it_should_returns_analyzed_result_when_saves_an_analyzed_result() throws InvalidRiskLevelException {
		// arrange
		Problem problem = new Problem(null, "BAD GRADES ON MATH");
		String actionPlan = "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER";
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		AnalyzedResult expected = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);
		
		// act
		AnalyzedResult actual = this.analyzedResultRepository.save(expected);		

		// assert
		assertEquals(expected, actual);
	}
	
	@Test
	public void it_should_returns_analyzed_results_given_a_problem() throws InvalidRiskLevelException {
		// arrange
		Problem problem = new Problem(null, "BAD GRADES ON MATH");
		String actionPlan = "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER";
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		entityManager.persistAndFlush(problem);

		AnalyzedResult analyzedResult = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);
		List<AnalyzedResult> expectedAnalyzedResultsList = Arrays.asList(analyzedResult);

		entityManager.persistAndFlush(analyzedResult);
		
		// act
		List<AnalyzedResult> actualAnalyzedResultsList = this.analyzedResultRepository.findAllByProblem(problem.getId());

		// assert
		assertEquals(expectedAnalyzedResultsList, actualAnalyzedResultsList);
	}
	
}
