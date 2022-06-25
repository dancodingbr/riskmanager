package com.dancodingbr.riskmanager.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.dancodingbr.riskmanager.models.AnalyzedResult;

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
	
	private AnalyzedResult analyzedResult;
	private String problem;
	private String actionPlan;
	private String probabilityLevel;
	private String impactLevel;
	
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

		this.analyzedResult = this.webClient.get()
				.uri(uriBuilder -> uriBuilder 
						.path("/analyzing-results/")
						.queryParam("problem", this.problem)
						.queryParam("actionPlan", this.actionPlan)
						.queryParam("probabilityLevel", this.probabilityLevel)
						.queryParam("impactLevel", this.impactLevel)
						.build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(AnalyzedResult.class)
				.block();
		
	}
	@Then("this results in the {string} chance to occurs the problem again")
	public void this_result_in_the_chance_to_occurs_the_problem_again(String riskLevel) {
		String actual = this.analyzedResult.getRiskLevel();
		assertEquals(riskLevel, actual);
	}
	
}
