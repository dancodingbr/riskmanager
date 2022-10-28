package com.dancodingbr.riskmanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodingbr.riskmanager.exception.InvalidImpactLevelException;
import com.dancodingbr.riskmanager.exception.InvalidProbabilityLevelException;
import com.dancodingbr.riskmanager.exception.InvalidRiskLevelException;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
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
	
	@PostMapping("/analyzed-result/")
	public ResponseEntity<String> postAnalyzedResult(@RequestBody AnalyzedResult analyzedResult) throws JsonProcessingException, InvalidImpactLevelException {
	    this.analyzingResultsService.save(analyzedResult);

		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("operation_status", "SUCCESS");
		
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node), httpHeaders, HttpStatus.OK);
	}
	
	@GetMapping("/analyzed-results/")
	public ResponseEntity<List<AnalyzedResult>> getAnalyzedResults(@RequestParam("problemId") Long problemId) {
		List<AnalyzedResult> analyzedResultsList = this.analyzingResultsService.getAnalyzedResults(problemId);
		
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<List<AnalyzedResult>>(analyzedResultsList, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/analyzed-results/")
	public ResponseEntity<String> postAnalyzedResults(@RequestBody List<AnalyzedResult> analyzedResultsList) throws JsonProcessingException, InvalidImpactLevelException {
	    this.analyzingResultsService.save(analyzedResultsList);

		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("operation_status", "SUCCESS");
		
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node), httpHeaders, HttpStatus.OK);
	}
	
}
