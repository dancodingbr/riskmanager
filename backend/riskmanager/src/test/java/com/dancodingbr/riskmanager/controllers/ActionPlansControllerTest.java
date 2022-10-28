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

import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.services.ActionPlansService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RunWith(SpringRunner.class)
@WebMvcTest(ActionPlansController.class)
public class ActionPlansControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ActionPlansService actionPlansService;

	@Test
	public void it_should_returns_http_200_response_when_gets_all_action_plans() throws Exception {

		// arrange
		List<ActionPlan> actionPlansList = Arrays.asList(
				new ActionPlan(1L, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER"),
				new ActionPlan(2L, "DECREASE 20% OF CALORIES CONSUMED PER DAY"));

		ObjectMapper mapper = new ObjectMapper();

		given(actionPlansService.getAllActionPlans()
				).willReturn(actionPlansList);
		
		// act and assert
		this.mockMvc.perform(MockMvcRequestBuilders.get("/action-plans/")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content()
						.json(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(actionPlansList)))
				.andReturn();

		// verify
		verify(actionPlansService).getAllActionPlans();

	}

	@Test
	public void it_should_returns_http_200_response_when_post_action_plan() throws Exception {

		// arrange
		Long id = 1L;
		String description = "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER";
		ActionPlan actionPlan = new ActionPlan(id, description);
		
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
	    node.put("operation_status", "SUCCESS");
		
		doNothing().when(actionPlansService).save(actionPlan);
		
		// act and assert
		this.mockMvc.perform(
				MockMvcRequestBuilders.post("/action-plans/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(new ObjectMapper().writeValueAsString(actionPlan))
				)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().string(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node)))
				.andReturn();
		
		// verify
		verify(actionPlansService, times(1)).save(actionPlan);
	}
	
}
