package com.dancodingbr.riskmanager.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.repositories.ActionPlansRepository;

@ExtendWith(MockitoExtension.class)
public class ActionPlansServiceTest {

	private ActionPlansService actionPlansService;

	@Mock
	private ActionPlansRepository actionPlansRepository;

	@BeforeEach
	public void setUp() {
		actionPlansService = new ActionPlansService(actionPlansRepository);
	}
	
	@Test
	public void it_should_returns_all_action_plans_when_get_all_action_plans() {
		// arrange
		List<ActionPlan> expectedActionPlansList = Arrays.asList(
				new ActionPlan(1L, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER"),
				new ActionPlan(2L, "DECREASE 20% OF CALORIES CONSUMED PER DAY"));
		
		given(actionPlansRepository.findAll()).willReturn(expectedActionPlansList);
		
		// act
		List<ActionPlan> actualActionPlansList = this.actionPlansService.getAllActionPlans();
		
		// assert
		assertEquals(expectedActionPlansList, actualActionPlansList);
		
		// verify
		verify(actionPlansRepository).findAll();
	}

	@Test
	public void it_should_returns_nothing_when_saves_an_action_plan_successfully() throws Exception {
		
		// arrange
		Long id = 1L;
		String description = "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER";
		ActionPlan actionPlan = new ActionPlan(id, description);

		given(actionPlansRepository.save(actionPlan)).willReturn(actionPlan);
		
		// act
		this.actionPlansService.save(actionPlan);
		
		// assert
		assertTrue(actionPlan instanceof ActionPlan);
		
		// verify
		verify(actionPlansRepository).save(any(ActionPlan.class));
	}

}
