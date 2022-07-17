package com.dancodingbr.riskmanager.services;

import org.springframework.stereotype.Service;

@Service
public class AnalyzingResultsService {

	public AnalyzingResultsService() {}

	public String calculateRiskLevel(String probabilityLevel, String impactLevel) {
		if (probabilityLevel.equals("RARE") && impactLevel.equals("HIGH")) {
			return "LOW";
		}
		else if (probabilityLevel.equals("INFREQUENT") && impactLevel.equals("MODERATE")) {
			return "LOW";
		}
		return null;
	}

}
