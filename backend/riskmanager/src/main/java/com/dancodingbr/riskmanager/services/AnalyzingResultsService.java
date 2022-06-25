package com.dancodingbr.riskmanager.services;

import org.springframework.stereotype.Service;

import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.repositories.AnalyzedResultRepository;

@Service
public class AnalyzingResultsService {

	private AnalyzedResultRepository analyzedResultRepository;

	public AnalyzingResultsService(AnalyzedResultRepository analyzedResultRepository) {
		this.analyzedResultRepository = analyzedResultRepository;
	}

	public AnalyzedResult getAnalyzedResult(String problem, String actionPlan, String probabilityLevel, String impactLevel) {
		return this.analyzedResultRepository.findByProblemAndActionPlanAndProbabilityLevelAndImpactLevel(problem, actionPlan, probabilityLevel, impactLevel);
	}

}
