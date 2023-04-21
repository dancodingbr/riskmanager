package com.dancodingbr.riskmanager.enums;

import static org.junit.Assert.assertThrows;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;

@ExtendWith(MockitoExtension.class)
public class RiskAssessmentMatrixTest {

	// 1 OF 5
	@Test
	public void it_should_returns_LOW_risk_given_IMMINENT_probability_level_and_VERY_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.IMMINENT;
		ImpactLevel impactLevel = ImpactLevel.VERY_LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}

	@Test
	public void it_should_returns_MODERATE_risk_given_IMMINENT_probability_level_and_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.IMMINENT;
		ImpactLevel impactLevel = ImpactLevel.LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}

	@Test
	public void it_should_returns_HIGH_risk_given_IMMINENT_probability_level_and_MODERATE_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.IMMINENT;
		ImpactLevel impactLevel = ImpactLevel.MODERATE;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.HIGH, riskLevel);
	}

	@Test
	public void it_should_returns_CRITICAL_risk_given_IMMINENT_probability_level_and_HIGH_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.IMMINENT;
		ImpactLevel impactLevel = ImpactLevel.HIGH;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.CRITICAL, riskLevel);
	}

	@Test
	public void it_should_returns_CRITICAL_risk_given_IMMINENT_probability_level_and_CRITICAL_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.IMMINENT;
		ImpactLevel impactLevel = ImpactLevel.CRITICAL;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.CRITICAL, riskLevel);
	}

	// 2 OF 5
	@Test
	public void it_should_returns_LOW_risk_given_FREQUENT_probability_level_and_VERY_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.FREQUENT;
		ImpactLevel impactLevel = ImpactLevel.VERY_LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}

	@Test
	public void it_should_returns_MODERATE_risk_given_FREQUENT_probability_level_and_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.FREQUENT;
		ImpactLevel impactLevel = ImpactLevel.LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}

	@Test
	public void it_should_returns_HIGH_risk_given_FREQUENT_probability_level_and_MODERATE_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.FREQUENT;
		ImpactLevel impactLevel = ImpactLevel.MODERATE;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.HIGH, riskLevel);
	}

	@Test
	public void it_should_returns_HIGH_risk_given_FREQUENT_probability_level_and_HIGH_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.FREQUENT;
		ImpactLevel impactLevel = ImpactLevel.HIGH;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.HIGH, riskLevel);
	}

	@Test
	public void it_should_returns_CRITICAL_risk_given_FREQUENT_probability_level_and_CRITICAL_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.FREQUENT;
		ImpactLevel impactLevel = ImpactLevel.CRITICAL;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.CRITICAL, riskLevel);
	}

	// 3 OF 5
	@Test
	public void it_should_returns_VERY_LOW_risk_given_OCCASIONAL_probability_level_and_VERY_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.OCCASIONAL;
		ImpactLevel impactLevel = ImpactLevel.VERY_LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.VERY_LOW, riskLevel);
	}

	@Test
	public void it_should_returns_LOW_risk_given_OCCASIONAL_probability_level_and_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.OCCASIONAL;
		ImpactLevel impactLevel = ImpactLevel.LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}

	@Test
	public void it_should_returns_MODERATE_risk_given_OCCASIONAL_probability_level_and_MODERATE_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.OCCASIONAL;
		ImpactLevel impactLevel = ImpactLevel.MODERATE;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}

	@Test
	public void it_should_returns_HIGH_risk_given_OCCASIONAL_probability_level_and_HIGH_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.OCCASIONAL;
		ImpactLevel impactLevel = ImpactLevel.HIGH;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.HIGH, riskLevel);
	}

	@Test
	public void it_should_returns_HIGH_risk_given_OCCASIONAL_probability_level_and_CRITICAL_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.OCCASIONAL;
		ImpactLevel impactLevel = ImpactLevel.CRITICAL;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.HIGH, riskLevel);
	}

	// 4 OF 5
	@Test
	public void it_should_returns_VERY_LOW_risk_given_INFREQUENT_probability_level_and_VERY_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.INFREQUENT;
		ImpactLevel impactLevel = ImpactLevel.VERY_LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.VERY_LOW, riskLevel);
	}

	@Test
	public void it_should_returns_VERY_LOW_risk_given_INFREQUENT_probability_level_and_LOW_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.INFREQUENT;
		ImpactLevel impactLevel = ImpactLevel.LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.VERY_LOW, riskLevel);
	}
	
	@Test
	public void it_should_returns_LOW_risk_given_INFREQUENT_probability_level_and_MODERATE_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.INFREQUENT;
		ImpactLevel impactLevel = ImpactLevel.MODERATE;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}
	
	@Test
	public void it_should_returns_MODERATE_risk_given_INFREQUENT_probability_level_and_HIGH_impact_level() throws InvalidRiskLevelException {

		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.INFREQUENT;
		ImpactLevel impactLevel = ImpactLevel.HIGH;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}
	
	@Test
	public void it_should_returns_MODERATE_risk_given_INFREQUENT_probability_level_and_CRITICAL_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.INFREQUENT;
		ImpactLevel impactLevel = ImpactLevel.CRITICAL;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}
	
	// 5 OF 5
	@Test
	public void it_should_returns_VERY_LOW_risk_given_RARE_probability_level_and_VERY_LOW_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.VERY_LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.VERY_LOW, riskLevel);
	}

	@Test
	public void it_should_returns_VERY_LOW_risk_given_RARE_probability_level_and_LOW_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.LOW;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.VERY_LOW, riskLevel);
	}

	@Test
	public void it_should_returns_LOW_risk_given_RARE_probability_level_and_MODERATE_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.MODERATE;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}

	@Test
	public void it_should_returns_LOW_risk_given_RARE_probability_level_and_HIGH_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.LOW, riskLevel);
	}

	@Test
	public void it_should_returns_MODERATE_risk_given_RARE_probability_level_and_CRITICAL_impact_level() throws InvalidRiskLevelException {
		
		// arrange
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.CRITICAL;

		// act
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
		
		// assert
		assertEquals(RiskLevel.MODERATE, riskLevel);
	}
	
	@Test
	public void it_should_throws_exception_when_risk_level_calculated_is_null() {

		Exception exception = assertThrows(InvalidRiskLevelException.class, () -> {
			
			// arrange
			ProbabilityLevel probabilityLevel = null;
			ImpactLevel impactLevel = null;
			
			// act
			RiskAssessmentMatrix.get(probabilityLevel, impactLevel);
			
		});
		
		// assert
		assertTrue(exception.getMessage().equals("Invalid risk level."));
	}
	
}
