package com.dancodingbr.riskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskAssessmentMatrix;
import com.dancodingbr.riskmanager.exception.InvalidImpactLevelException;
import com.dancodingbr.riskmanager.exception.InvalidProbabilityLevelException;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.repositories.AnalyzedResultRepository;

@Service
public class AnalyzingResultsService {

	private AnalyzedResultRepository analyzedResultRepository;

	public AnalyzingResultsService(AnalyzedResultRepository analyzedResultRepository) {
		this.analyzedResultRepository = analyzedResultRepository;
	}

	public String calculateRiskLevel(String probabilityLevel, String impactLevel) throws InvalidProbabilityLevelException, InvalidImpactLevelException, InvalidRiskLevelException {
		ProbabilityLevel probabilityLevelEnum = null;
		ImpactLevel impactLevelEnum = null;
		
		try {
			probabilityLevelEnum = ProbabilityLevel.valueOf(probabilityLevel);
		} catch(IllegalArgumentException iae) {
			throw new InvalidProbabilityLevelException("Invalid probability level.");
		} catch(NullPointerException iae) {
			throw new InvalidProbabilityLevelException("Invalid probability level.");
		}
		
		try {
			impactLevelEnum = ImpactLevel.valueOf(impactLevel);
		} catch(IllegalArgumentException iae) {
			throw new InvalidImpactLevelException("Invalid impact level.");
		} catch(NullPointerException iae) {
			throw new InvalidImpactLevelException("Invalid impact level.");
		}
		
		return RiskAssessmentMatrix.get(probabilityLevelEnum, impactLevelEnum).name();
	}

	public void save(AnalyzedResult analyzedResult) {
		this.analyzedResultRepository.save(analyzedResult);
	}

	public List<AnalyzedResult> getAnalyzedResults(Long problemId) {
		return this.analyzedResultRepository.findAllByProblem(problemId);
	}

}
