package com.dancodingbr.riskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.services.AnalyzingResultsService;

@RestController
public class AnalyzingResultsController {

	@Autowired
	private AnalyzingResultsService analyzingResultsService;

	@GetMapping("/analyzing-results/")
	public AnalyzedResult getAnalizedResult(@RequestParam("problem") String problem,
			@RequestParam("actionPlan") String actionPlan, @RequestParam("probabilityLevel") String probabilityLevel,
			@RequestParam("impactLevel") String impactLevel) {
		return analyzingResultsService.getAnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel);
	}
}
