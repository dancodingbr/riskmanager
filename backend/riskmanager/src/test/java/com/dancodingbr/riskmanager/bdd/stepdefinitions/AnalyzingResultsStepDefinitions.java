package com.dancodingbr.riskmanager.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AnalyzingResultsStepDefinitions {
	
	private WebClient webClient;
	@Value("${server.servlet.context-path}")
	private String contextPath;
	@Value("${server.port}")
	private int port;
	
	private String probabilityLevel;
	private String impactLevel;
	private ResponseEntity<String> response;

	private String riskLevel;
	private String problem;
	private String actionPlan;
	
	@Before
	public void before() {
		this.webClient = WebClient.builder()
				.baseUrl("http://localhost:" + this.port + this.contextPath)
				.build();
	}
	
	@Given("a related {string}")
	public void a_related(String problem) {
		this.problem = problem;
	}
	@Given("an {string} done")
	public void an_done(String actionPlan) {
		this.actionPlan = actionPlan;
	}
	@Given("a {string} occurrence of this mitigated problem")
	public void a_occurrence_of_this_mitigated_problem(String probabilityLevel) {
		this.probabilityLevel = probabilityLevel;
	}
	@Given("an {string} of this mitigated problem")
	public void an_impact_of_this_mitigated_problem(String impactLevel) {
		this.impactLevel = impactLevel;
	}
	@When("the system calculates a risk level using a risk assessment matrix")
	public void the_system_calculates_a_risk_level_using_a_risk_assessment_matrix() {

		this.response = this.webClient.get()
				.uri(uriBuilder -> uriBuilder 
						.path("/analyzing-results/")
						.queryParam("probabilityLevel", this.probabilityLevel)
						.queryParam("impactLevel", this.impactLevel)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(String.class)
				.block();

	}
	@Then("this results in the {string} chance to occurs the problem again")
	public void this_result_in_the_chance_to_occurs_the_problem_again(String riskLevel) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.response.getBody());
		assertEquals(riskLevel, jsonNode.get("risk_level").asText());
	}
	

	@Given("a {string} risk calculated")
	public void a_risk_calculated(String riskLevel) {
		this.riskLevel = riskLevel;
	}
	@When("the user saves this analyzed result")
	public void the_user_saves_the_analyzed_result() {

		AnalyzedResult analyzedResult = new AnalyzedResult(
					this.problem,
					this.actionPlan,
					ProbabilityLevel.valueOf(probabilityLevel),
					ImpactLevel.valueOf(this.impactLevel),
					RiskLevel.valueOf(this.riskLevel)
				);

		this.response = this.webClient.post()
				.uri("/analyzed-results/")
	            .contentType(MediaType.APPLICATION_JSON)
				.bodyValue(analyzedResult)
				.retrieve()
				.toEntity(String.class)
				.block();
		
	}
	@Then("shows that operation was {string}")
	public void shows_that_operation_was(String operationStatus) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.response.getBody());
		assertEquals(operationStatus, jsonNode.get("operation_status").asText());
	}
	
}
