package com.dancodingbr.riskmanager.controllers;

import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dancodingbr.riskmanager.models.AnalyzedResult;
import com.dancodingbr.riskmanager.services.AnalyzingResultsService;

@RunWith(SpringRunner.class)
@WebMvcTest(AnalyzingResultsController.class)
public class AnalyzingResultsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AnalyzingResultsService analyzingResultsService;

	@Test
	public void it_should_returns_http_200_response_when_gets_valid_analyzed_result() throws Exception {

		// arrange
		String problem = "Bad grades on math";
		String actionPlan = "Study 8 hours per week on next semester";
		String probabilityLevel = "RARE";
		String impactLevel = "HIGH";
		
		given(analyzingResultsService.getAnalyzedResult(
				anyString(), anyString(), anyString(), anyString())
			).willReturn(new AnalyzedResult(problem, actionPlan, probabilityLevel, impactLevel));
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.get("/analyzing-results/")
					.param("problem", problem)
					.param("actionPlan", actionPlan)
					.param("probabilityLevel", probabilityLevel)
					.param("impactLevel", impactLevel)
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();

		// verify
		verify(analyzingResultsService).getAnalyzedResult(anyString(), anyString(), anyString(), anyString());
	}
}
