package com.dancodingbr.riskmanager.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskAssessmentMatrix;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.models.AnalyzedResult;

@DataJpaTest
public class AnalyzingResultsRepositoryTest {
	
	@Autowired
	private AnalyzedResultRepository analyzedResultRepository;
	
	@Test
	public void it_should_returns_analyzed_result_when_saves_an_analyzed_result() throws InvalidRiskLevelException {
		// arrange
		String problem = "BAD GRADES ON MATH";
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
	
}
