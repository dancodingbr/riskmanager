package com.dancodingbr.riskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dancodingbr.riskmanager.models.ActionPlan;
import com.dancodingbr.riskmanager.repositories.ActionPlansRepository;

@Service
public class ActionPlansService {

	private ActionPlansRepository actionPlansRepository;

	public ActionPlansService(ActionPlansRepository actionPlansRepository) {
		this.actionPlansRepository = actionPlansRepository;
	}

	public List<ActionPlan> getAllActionPlans() {
		return this.actionPlansRepository.findAll();
	}

	public void save(ActionPlan actionPlan) {
		this.actionPlansRepository.save(actionPlan);
	}

}
