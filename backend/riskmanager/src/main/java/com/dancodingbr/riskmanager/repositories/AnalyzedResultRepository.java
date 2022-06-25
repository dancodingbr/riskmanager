package com.dancodingbr.riskmanager.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dancodingbr.riskmanager.models.AnalyzedResult;

public interface AnalyzedResultRepository extends CrudRepository<AnalyzedResult, String> {

	AnalyzedResult findByProblemAndActionPlanAndProbabilityLevelAndImpactLevel(String problem, String actionPlan,
			String probabilityLevel, String impactLevel);

}
