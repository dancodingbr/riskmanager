package com.dancodingbr.riskmanager.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnalyzedResultTest {

	
	@Test
	public void it_should_returns_risk_level_given_probability_level_and_impact_level() {

		// arrange
		String problem = "Bad grades on math";
		String actionPlan = "Study 8 hours per week on next semester";
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		String riskLevel = "LOW";
		
		AnalyzedResult analyzedResult = new AnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel);
		
		// act
		String actual = analyzedResult.getRiskLevel();
		
		// assert
		assertEquals(riskLevel, actual);
	}
}
