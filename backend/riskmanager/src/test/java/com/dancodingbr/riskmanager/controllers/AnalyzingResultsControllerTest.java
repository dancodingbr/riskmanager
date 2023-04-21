package com.dancodingbr.riskmanager.controllers;

import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskAssessmentMatrix;
import com.dancodingbr.riskmanager.enums.RiskLevel;
import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.services.AnalyzingResultsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringRunner.class)
@WebMvcTest(AnalyzingResultsController.class)
public class AnalyzingResultsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnalyzingResultsService analyzingResultsService;

	@Test
	public void it_should_returns_http_200_response_when_gets_valid_risk_level() throws Exception {

		// arrange
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		String riskLevel = "LOW";
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("risk_level", riskLevel);
		
		given(analyzingResultsService.calculateRiskLevel(anyString(), anyString())
			).willReturn(riskLevel);
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/analyzing-results/")
					.param("probabilityLevel", probabilityLevel)
					.param("impactLevel", impactLevel)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node)))
				.andReturn();

		// verify
		verify(analyzingResultsService).calculateRiskLevel(anyString(), anyString());
	}

	@Test
	public void it_should_returns_http_200_response_when_post_analyzed_result() throws Exception {

		// arrange
		Problem problem = new Problem(1L, "BAD GRADES ON MATH");
		ActionPlan actionPlan = new ActionPlan(1L, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER");
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		AnalyzedResult analyzedResult = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("operation_status", "SUCCESS");
		
		doNothing().when(analyzingResultsService).save(analyzedResult);
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/analyzed-result/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(analyzedResult))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node)))
				.andReturn();
		
		// verify
		verify(analyzingResultsService, times(1)).save(analyzedResult);
	}

	@Test
	public void it_should_returns_http_200_response_when_gets_analyzed_results_given_a_problem() throws Exception {

		// arrange
		Problem problem = new Problem(1L, "BAD GRADES ON MATH");
		ActionPlan actionPlan = new ActionPlan(null, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER");
		ProbabilityLevel probabilityLevel = ProbabilityLevel.RARE;
		ImpactLevel impactLevel = ImpactLevel.HIGH;
		RiskLevel riskLevel = RiskAssessmentMatrix.get(probabilityLevel, impactLevel);

		AnalyzedResult analyzedResult = new AnalyzedResult(
				problem,
				actionPlan,
				probabilityLevel,
				impactLevel,
				riskLevel
			);
		List<AnalyzedResult> analyzedResultsList = Arrays.asList(analyzedResult);
		
		ObjectMapper mapper = new ObjectMapper();
		
		given(analyzingResultsService.getAnalyzedResults(anyLong())
			).willReturn(analyzedResultsList);
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/analyzed-results/")
					.param("problemId", String.valueOf(problem.getId()))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(analyzedResultsList)))
				.andReturn();

		// verify
		verify(analyzingResultsService).getAnalyzedResults(anyLong());
	}

	@Test
	public void it_should_returns_http_200_response_when_post_analyzed_results_list() throws Exception {

		// arrange
		List<AnalyzedResult> analyzedResultsList = new ArrayList<AnalyzedResult>();
		analyzedResultsList.add(
				new AnalyzedResult(
						new Problem(1L, "BAD GRADES ON MATH"),
						new ActionPlan(1L, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER"),
						ProbabilityLevel.RARE,
						ImpactLevel.HIGH,
						RiskAssessmentMatrix.get(ProbabilityLevel.RARE, ImpactLevel.HIGH)
					)				
				);
		analyzedResultsList.add(
				new AnalyzedResult(
						new Problem(2L, "BODY WEIGHT RAISING"),
						new ActionPlan(2L, "DECREASE 20% OF CALORIES CONSUMED PER DAY"),
						ProbabilityLevel.INFREQUENT,
						ImpactLevel.MODERATE,
						RiskAssessmentMatrix.get(ProbabilityLevel.INFREQUENT, ImpactLevel.MODERATE)
					)				
				);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("operation_status", "SUCCESS");
		
		doNothing().when(analyzingResultsService).save(analyzedResultsList);

		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/analyzed-results/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(analyzedResultsList))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node)))
				.andReturn();

		// verify
		verify(analyzingResultsService, times(1)).save(analyzedResultsList);

	}
	
}
