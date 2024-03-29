package com.dancodingbr.riskmanager.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.models.Problem;
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
	private ResponseEntity<String> responseEntityString;
	private ResponseEntity<List<AnalyzedResult>> responseEntityAnalyzedResultList;

	private String riskLevel;
	private ActionPlan actionPlan;
	private Problem problem;
	
	private List<AnalyzedResult> analyzedResultsExpectedList;

	@Before
	public void before() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:" + this.port + this.contextPath).build();
		this.problem = new Problem();
		this.actionPlan = new ActionPlan();
		this.analyzedResultsExpectedList = new ArrayList<AnalyzedResult>();
	}

	@Given("a related {string},{string}")
	public void a_related(String problemId, String problemDescription) {
		this.problem.setId(Long.parseLong(problemId));
		this.problem.setDescription(problemDescription);

		this.responseEntityString = this.webClient.post().uri("/problems/")
				.contentType(MediaType.APPLICATION_JSON).bodyValue(this.problem).retrieve().toEntity(String.class)
				.block();
	}

	@Given("an {string},{string} done")
	public void an_done(String actionPlanId, String actionPlanDescription) {
		this.actionPlan.setId(Long.parseLong(actionPlanId));
		this.actionPlan.setDescription(actionPlanDescription);

		this.responseEntityString = this.webClient.post().uri("/action-plans/")
				.contentType(MediaType.APPLICATION_JSON).bodyValue(this.actionPlan).retrieve().toEntity(String.class)
				.block();
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

		this.responseEntityString = this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/analyzing-results/")
						.queryParam("probabilityLevel", this.probabilityLevel)
						.queryParam("impactLevel", this.impactLevel).build())
				.accept(MediaType.APPLICATION_JSON).retrieve().toEntity(String.class).block();

	}

	@Then("this results in the {string} chance to occurs the problem again")
	public void this_result_in_the_chance_to_occurs_the_problem_again(String riskLevel)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.responseEntityString.getBody());
		assertEquals(riskLevel, jsonNode.get("risk_level").asText());
	}

	@Given("a {string} risk calculated")
	public void a_risk_calculated(String riskLevel) {
		this.riskLevel = riskLevel;
	}

	@When("the user saves this analyzed result")
	public void the_user_saves_the_analyzed_result() {
		
		AnalyzedResult analyzedResult = new AnalyzedResult(this.problem, this.actionPlan,
				ProbabilityLevel.valueOf(probabilityLevel), ImpactLevel.valueOf(this.impactLevel),
				RiskLevel.valueOf(this.riskLevel));

		this.responseEntityString = this.webClient.post().uri("/analyzed-result/")
				.contentType(MediaType.APPLICATION_JSON).bodyValue(analyzedResult).retrieve().toEntity(String.class)
				.block();

	}

	@Then("shows that operation was {string}")
	public void shows_that_operation_was(String operationStatus) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(this.responseEntityString.getBody());
		assertEquals(operationStatus, jsonNode.get("operation_status").asText());
	}

	@Given("the <analyzed_results> stored")
	public void the_analyzed_results_stored(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

		for (int i=0; i<rows.size(); i++) {
			String[] problemStr = rows.get(i).get("problem").replace("'", "").split(",");
			String[] actionPlanStr = rows.get(i).get("action_plan").replace("'", "").split(",");
			Problem problem = new Problem(Long.parseLong(problemStr[0]), problemStr[1]);
			ActionPlan actionPlan = new ActionPlan(Long.parseLong(actionPlanStr[0]), actionPlanStr[1]);
			ProbabilityLevel probabilityLevel = ProbabilityLevel.valueOf(rows.get(i).get("probability_level").replace("'", "")); 
			ImpactLevel impactLevel = ImpactLevel.valueOf(rows.get(i).get("impact_level").replace("'", ""));
			RiskLevel riskLevel = RiskLevel.valueOf(rows.get(i).get("risk_level").replace("'", ""));
			this.analyzedResultsExpectedList.add(
					new AnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel, riskLevel)
				);
		}

		this.responseEntityString = this.webClient.post().uri("/analyzed-results/")
				.contentType(MediaType.APPLICATION_JSON).bodyValue(analyzedResultsExpectedList).retrieve().toEntity(String.class)
				.block();
	}
	
	@When("the system gets the analyzed results associated")
	public void the_system_gets_the_analyzed_results_associated() {

		this.responseEntityAnalyzedResultList = this.webClient.get()
				.uri(uriBuilder -> uriBuilder.path("/analyzed-results/")
						.queryParam("problemId", this.problem.getId()).build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntityList(AnalyzedResult.class)
				.block();
	}

	@Then("shows {string},{string}")
	public void shows(String actionPlanId, String actionPlanDescription) throws JsonMappingException, JsonProcessingException {
		List<AnalyzedResult> analyzedResultsActualList = this.responseEntityAnalyzedResultList.getBody();
		assertEquals(Long.parseLong(actionPlanId), analyzedResultsActualList.get(0).getActionPlan().getId());
		assertEquals(actionPlanDescription, analyzedResultsActualList.get(0).getActionPlan().getDescription());
	}
	
	@Then("{string} of each analyzed result")
	public void of_each_analyzed_result(String riskLevel) {
		List<AnalyzedResult> analyzedResultsList = this.responseEntityAnalyzedResultList.getBody();
		assertEquals(riskLevel, analyzedResultsList.get(0).getRiskLevel().name());
	}
	
}
