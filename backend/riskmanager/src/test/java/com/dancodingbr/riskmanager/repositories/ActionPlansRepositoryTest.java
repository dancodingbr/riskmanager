package com.dancodingbr.riskmanager.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.dancodingbr.riskmanager.models.ActionPlan;

@DataJpaTest
public class ActionPlansRepositoryTest {

	@Autowired
	private ActionPlansRepository actionPlansRepository;
	
	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void it_should_returns_all_action_plans_list_when_finds_all() {

		// arrange
		List<ActionPlan> expectedActionPlansList = Arrays.asList(
				new ActionPlan(null, "STUDY 8 HOURS PER WEEK ON NEXT SEMESTER"),
				new ActionPlan(null, "DECREASE 20% OF CALORIES CONSUMED PER DAY"));
		
		for (ActionPlan actionPlan : expectedActionPlansList) {
			entityManager.persistAndFlush(actionPlan);
		}
		
		// act
		List<ActionPlan> actualActionPlansList = this.actionPlansRepository.findAll();
		
		// assert
		assertEquals(expectedActionPlansList, actualActionPlansList);
	}
}
