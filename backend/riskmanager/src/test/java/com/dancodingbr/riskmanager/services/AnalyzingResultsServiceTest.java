package com.dancodingbr.riskmanager.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AnalyzingResultsServiceTest {

	private AnalyzingResultsService analyzingResultsService;

	@BeforeEach
	public void setUp() {
		analyzingResultsService = new AnalyzingResultsService();
	}

	@Test
	public void it_should_returns_a_risk_level_calculated_given_probability_level_and_impact_level() {

		// arrange
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		String expectedRiskLevel = "LOW";
		
		// act
		String actualRiskLevel = this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(expectedRiskLevel, actualRiskLevel);
	}
	
}
