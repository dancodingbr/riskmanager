package com.dancodingbr.riskmanager.controllers;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
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
import com.fasterxml.jackson.databind.node.ObjectNode;

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

	@Test
	public void it_should_returns_http_200_response_when_post_problem() throws Exception {

		// arrange
		Long id = 1L;
		String description = "BAD GRADES ON MATH";
		Problem problem = new Problem(id, description);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("operation_status", "SUCCESS");
		
		doNothing().when(problemsService).save(problem);
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/problems/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(problem))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node)))
				.andReturn();
		
		// verify
		verify(problemsService, times(1)).save(problem);
	}
}
