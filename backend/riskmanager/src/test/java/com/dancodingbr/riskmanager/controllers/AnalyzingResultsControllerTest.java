package com.dancodingbr.riskmanager.controllers;

import static org.mockito.BDDMockito.*;
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
import com.dancodingbr.riskmanager.models.AnalyzedResult;
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
		String problem = "BAD GRADES ON MATH";
		String actionPlan = "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER";
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
				MockMvcRequestBuilders.post("/analyzed-results/")
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
	
}
