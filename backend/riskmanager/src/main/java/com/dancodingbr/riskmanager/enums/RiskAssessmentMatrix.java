package com.dancodingbr.riskmanager.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.util.Pair;

import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;

public class RiskAssessmentMatrix {

	private static Map<Pair<ProbabilityLevel,ImpactLevel>, RiskLevel> matrix;
	
	static {
		matrix = new HashMap<Pair<ProbabilityLevel,ImpactLevel>, RiskLevel>();
		
		matrix.put(Pair.of(ProbabilityLevel.IMMINENT, ImpactLevel.VERY_LOW), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.IMMINENT, ImpactLevel.LOW), RiskLevel.MODERATE);
		matrix.put(Pair.of(ProbabilityLevel.IMMINENT, ImpactLevel.MODERATE), RiskLevel.HIGH);
		matrix.put(Pair.of(ProbabilityLevel.IMMINENT, ImpactLevel.HIGH), RiskLevel.CRITICAL);
		matrix.put(Pair.of(ProbabilityLevel.IMMINENT, ImpactLevel.CRITICAL), RiskLevel.CRITICAL);
		
		matrix.put(Pair.of(ProbabilityLevel.FREQUENT, ImpactLevel.VERY_LOW), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.FREQUENT, ImpactLevel.LOW), RiskLevel.MODERATE);
		matrix.put(Pair.of(ProbabilityLevel.FREQUENT, ImpactLevel.MODERATE), RiskLevel.HIGH);
		matrix.put(Pair.of(ProbabilityLevel.FREQUENT, ImpactLevel.HIGH), RiskLevel.HIGH);
		matrix.put(Pair.of(ProbabilityLevel.FREQUENT, ImpactLevel.CRITICAL), RiskLevel.CRITICAL);

		matrix.put(Pair.of(ProbabilityLevel.OCCASIONAL, ImpactLevel.VERY_LOW), RiskLevel.VERY_LOW);
		matrix.put(Pair.of(ProbabilityLevel.OCCASIONAL, ImpactLevel.LOW), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.OCCASIONAL, ImpactLevel.MODERATE), RiskLevel.MODERATE);
		matrix.put(Pair.of(ProbabilityLevel.OCCASIONAL, ImpactLevel.HIGH), RiskLevel.HIGH);
		matrix.put(Pair.of(ProbabilityLevel.OCCASIONAL, ImpactLevel.CRITICAL), RiskLevel.HIGH);

		matrix.put(Pair.of(ProbabilityLevel.INFREQUENT, ImpactLevel.VERY_LOW), RiskLevel.VERY_LOW);
		matrix.put(Pair.of(ProbabilityLevel.INFREQUENT, ImpactLevel.LOW), RiskLevel.VERY_LOW);
		matrix.put(Pair.of(ProbabilityLevel.INFREQUENT, ImpactLevel.MODERATE), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.INFREQUENT, ImpactLevel.HIGH), RiskLevel.MODERATE);
		matrix.put(Pair.of(ProbabilityLevel.INFREQUENT, ImpactLevel.CRITICAL), RiskLevel.MODERATE);

		matrix.put(Pair.of(ProbabilityLevel.RARE, ImpactLevel.VERY_LOW), RiskLevel.VERY_LOW);
		matrix.put(Pair.of(ProbabilityLevel.RARE, ImpactLevel.LOW), RiskLevel.VERY_LOW);
		matrix.put(Pair.of(ProbabilityLevel.RARE, ImpactLevel.MODERATE), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.RARE, ImpactLevel.HIGH), RiskLevel.LOW);
		matrix.put(Pair.of(ProbabilityLevel.RARE, ImpactLevel.CRITICAL), RiskLevel.MODERATE);
	}

	public static RiskLevel get(ProbabilityLevel probabilityLevel, ImpactLevel impactLevel) throws InvalidRiskLevelException {
		RiskLevel riskLevel = null;
		try {
			riskLevel = matrix.get(Pair.of(probabilityLevel, impactLevel));
		} catch(IllegalArgumentException iae) {
			throw new InvalidRiskLevelException("Invalid risk level.");
		}
		if (riskLevel == null) {
			throw new InvalidRiskLevelException("Invalid risk level.");
		}
		return riskLevel;
	}

}
