package com.dancodingbr.riskmanager.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.models.AnalyzedResult;
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
	public void it_should_returns_analyzed_result_when_this_was_found() {

		// arrange
		String problem = "Bad grades on math";
		String actionPlan = "Study 8 hours per week on next semester";
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		
		given(this.analyzedResultRepository.findByProblemAndActionPlanAndProbabilityLevelAndImpactLevel(problem, actionPlan, probabilityLevel, impactLevel)).willReturn(new AnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel));
		
		// act
		AnalyzedResult analyzedResult = this.analyzingResultsService.getAnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel);
		
		// assert
		assertTrue(analyzedResult instanceof AnalyzedResult);
		
		// verify
		verify(analyzedResultRepository).findByProblemAndActionPlanAndProbabilityLevelAndImpactLevel(anyString(), anyString(), anyString(), anyString());
	}
	
}
