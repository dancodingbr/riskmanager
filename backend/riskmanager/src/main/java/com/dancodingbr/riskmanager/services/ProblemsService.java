package com.dancodingbr.riskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.repositories.ProblemsRepository;

@Service
public class ProblemsService {

	private ProblemsRepository problemsRepository;

	public ProblemsService(ProblemsRepository problemsRepository) {
		this.problemsRepository = problemsRepository;
	}

	public List<Problem> getAllProblems() {
		return this.problemsRepository.findAll();
	}

	public void save(Problem problem) {
		this.problemsRepository.save(problem);
	}

}
