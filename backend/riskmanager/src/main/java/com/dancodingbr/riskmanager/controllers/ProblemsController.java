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

import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.services.ProblemsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
public class ProblemsController {

	@Autowired
	private ProblemsService problemsService;

	@GetMapping("/problems/")
	public ResponseEntity<List<Problem>> getProblems() {
		List<Problem> problemsList = this.problemsService.getAllProblems();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<List<Problem>>(problemsList, httpHeaders, HttpStatus.OK);
	}
	
	@PostMapping("/problems/")
	public ResponseEntity<String> postProblem(@RequestBody Problem problem) throws JsonProcessingException {
	    this.problemsService.save(problem);

		final HttpHeaders httpHeaders= new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		node.put("operation_status", "SUCCESS");
		
		return new ResponseEntity<String>(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node), httpHeaders, HttpStatus.OK);
	}
	
}
