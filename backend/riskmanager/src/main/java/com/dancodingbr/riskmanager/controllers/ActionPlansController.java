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
import org.springframework.web.bind.annotation.RestController;

import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.services.ActionPlansService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ActionPlansController {

	@Autowired
	private ActionPlansService actionPlansService;

	@GetMapping("/action-plans/")
	public ResponseEntity<List<ActionPlan>> getActionPlans() {
		List<ActionPlan> actionPlansList = this.actionPlansService.getAllActionPlans();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<List<ActionPlan>>(actionPlansList, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/action-plans/")
	public ResponseEntity<String> postActionPlan(@RequestBody ActionPlan actionPlan) throws JsonProcessingException {
	    this.actionPlansService.save(actionPlan);

		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("operation_status", "SUCCESS");
		
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node), httpHeaders, HttpStatus.OK);
	}

}
