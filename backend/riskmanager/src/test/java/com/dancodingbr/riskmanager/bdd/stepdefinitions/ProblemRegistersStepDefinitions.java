package com.dancodingbr.riskmanager.bdd.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;

import com.dancodingbr.riskmanager.models.Problem;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProblemRegistersStepDefinitions {

	private WebClient webClient;
	@Value("${server.servlet.context-path}")
	private String contextPath;
	@Value("${server.port}")
	private int port;
	private List<Problem> problems;
	private ResponseEntity<List<Problem>> responseEntityProblemList;

	@Before
	public void before() {
		this.webClient = WebClient.builder().baseUrl("http://localhost:" + this.port + this.contextPath).build();
		this.problems = new ArrayList<Problem>();
	}
	
	@Given("a problems saved by the user")
	public void a_problems_saved_by_the_user(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> columns : rows) {
			problems.add(new Problem(Long.parseLong(columns.get("id")), columns.get("description")));
		}
	}
	@When("the system gets all problems")
	public void the_system_gets_all_problems() {

		this.responseEntityProblemList = this.webClient.get()
				.uri("/problems/")
				.accept(MediaType.APPLICATION_JSON).retrieve().toEntityList(Problem.class).block();
	}
	@Then("shows these problems registered")
	public void shows_these_problems_registered() {
		List<Problem> actualProblemsList = this.responseEntityProblemList.getBody();
		for (int i=0; i < this.problems.size(); i++) {
			assertEquals(this.problems.get(i).getId(), actualProblemsList.get(i).getId());
			assertEquals(this.problems.get(i).getDescription(), actualProblemsList.get(i).getDescription());
		}
	}
	
}
