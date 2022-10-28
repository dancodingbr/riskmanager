package com.dancodingbr.riskmanager.services;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskAssessmentMatrix;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.exception.InvalidImpactLevelException;
import com.dancodingbr.riskmanager.exception.InvalidProbabilityLevelException;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.repositories.AnalyzedResultRepository;

@ExtendWith(MockitoExtension.class)
public class AnalyzingResultsServiceTest {

	private AnalyzingResultsService analyzingResultsService;

	@Mock
	private AnalyzedResultRepository analyzedResultRepository;
	
	@BeforeEach
	public void setUp() {
		analyzingResultsService = new AnalyzingResultsService(analyzedResultRepository);
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

	@Test
	public void it_should_returns_nothing_when_saves_an_analyzed_result_successfully() throws Exception {
		
		// arrange
		Problem problem = new Problem(1L, "BAD GRADES ON MATH");
		ActionPlan actionPlan = new ActionPlan(null, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER");
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		AnalyzedResult analyzedResult = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);

		given(analyzedResultRepository.save(analyzedResult)).willReturn(analyzedResult);
		
		// act
		this.analyzingResultsService.save(analyzedResult);
		
		// assert
		assertTrue(analyzedResult instanceof AnalyzedResult);
		
		// verify
		verify(analyzedResultRepository).save(any(AnalyzedResult.class));
	}

	@Test
	public void it_should_returns_analyzed_results_given_a_problem() throws InvalidRiskLevelException {

		// arrange
		Problem problem = new Problem(1L, "BAD GRADES ON MATH");
		ActionPlan actionPlan = new ActionPlan(null, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER");
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		AnalyzedResult analyzedResult = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);
		List<AnalyzedResult> expectedAnalyzedResultsList = Arrays.asList(analyzedResult);

		given(analyzedResultRepository.findAllByProblem(problem.getId())).willReturn(expectedAnalyzedResultsList);
		
		// act
		List<AnalyzedResult> actualAnalyzedResultsList = this.analyzingResultsService.getAnalyzedResults(problem.getId());
		
		// assert
		assertTrue(analyzedResult instanceof AnalyzedResult);
		assertEquals(expectedAnalyzedResultsList, actualAnalyzedResultsList);
		
		// verify
		verify(analyzedResultRepository).findAllByProblem(anyLong());
	}
	
}
