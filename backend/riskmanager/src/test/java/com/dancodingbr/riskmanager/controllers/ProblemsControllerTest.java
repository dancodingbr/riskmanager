package com.dancodingbr.riskmanager.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

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

import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.services.ProblemsService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(ProblemsController.class)
public class ProblemsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProblemsService problemsService;

	@Test
	public void it_should_returns_http_200_response_when_gets_all_problems() throws Exception {

		// arrange
		List<Problem> problemsList = Arrays.asList(
				new Problem(1L, "BAD GRADES ON MATH"),
				new Problem(2L, "BODY WEIGHT RAISING"));

		ObjectMapper mapper = new ObjectMapper();

		given(problemsService.getAllProblems()
				).willReturn(problemsList);
		
		// act and assert
		this.mockMvc.perform(MockMvcRequestBuilders.get("/problems/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content()
						.json(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(problemsList)))
				.andReturn();

		// verify
		verify(problemsService).getAllProblems();

	}
}
