package com.dancodingbr.riskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodingbr.riskmanager.exception.InvalidImpactLevelException;
import com.dancodingbr.riskmanager.exception.InvalidProbabilityLevelException;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.services.AnalyzingResultsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class AnalyzingResultsController {

	@Autowired
	private AnalyzingResultsService analyzingResultsService;

	@GetMapping("/analyzing-results/")
	public ResponseEntity<String> getRiskLevel(@RequestParam("probabilityLevel") String probabilityLevel,
			@RequestParam("impactLevel") String impactLevel) throws JsonProcessingException, InvalidProbabilityLevelException, InvalidImpactLevelException, InvalidRiskLevelException {
		String riskLevel = analyzingResultsService.calculateRiskLevel(probabilityLevel, impactLevel);

		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("risk_level", riskLevel);
		
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node), httpHeaders, HttpStatus.OK);		
	}
}
