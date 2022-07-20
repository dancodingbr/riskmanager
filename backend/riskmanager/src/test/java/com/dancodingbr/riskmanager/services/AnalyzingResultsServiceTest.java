package com.dancodingbr.riskmanager.services;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.exception.InvalidImpactLevelException;
import com.dancodingbr.riskmanager.exception.InvalidProbabilityLevelException;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;

@ExtendWith(MockitoExtension.class)
public class AnalyzingResultsServiceTest {

	private AnalyzingResultsService analyzingResultsService;

	@BeforeEach
	public void setUp() {
		analyzingResultsService = new AnalyzingResultsService();
	}

	@Test
	public void it_should_returns_a_risk_level_calculated_given_probability_level_and_impact_level() throws InvalidProbabilityLevelException, InvalidImpactLevelException, InvalidRiskLevelException {

		// arrange
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		String expectedRiskLevel = "LOW";
		
		// act
		String actualRiskLevel = this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(expectedRiskLevel, actualRiskLevel);
	}

	@Test
	public void it_should_throws_exception_given_an_invalid_probability_level_and_valid_impact_level() {

		Exception exception = assertThrows(InvalidProbabilityLevelException.class, () -> {
			
			// arrange
			String probabilityLevel = "rAr";
			String impactLevel = "HIGH";
			
			// act
			this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
			
		});
		
		// assert
		assertTrue(exception.getMessage().equals("Invalid probability level."));

		exception = assertThrows(InvalidProbabilityLevelException.class, () -> {
			
			// arrange
			String probabilityLevel = null;
			String impactLevel = "HIGH";
			
			// act
			this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
			
		});
		
		// assert
		assertTrue(exception.getMessage().equals("Invalid probability level."));
	}

	@Test
	public void it_should_throws_exception_given_an_valid_probability_level_and_invalid_impact_level() {

		Exception exception = assertThrows(InvalidImpactLevelException.class, () -> {
			
			// arrange
			String probabilityLevel = "RARE";
			String impactLevel = "hIg";
			
			// act
			this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
			
		});
		
		// assert
		assertTrue(exception.getMessage().equals("Invalid impact level."));

		exception = assertThrows(InvalidImpactLevelException.class, () -> {
			
			// arrange
			String probabilityLevel = "RARE";
			String impactLevel = null;
			
			// act
			this.analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);
			
		});
		
		// assert
		assertTrue(exception.getMessage().equals("Invalid impact level."));
	}

}
