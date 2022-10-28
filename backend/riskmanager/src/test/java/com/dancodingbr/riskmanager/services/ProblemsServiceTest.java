package com.dancodingbr.riskmanager.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dancodingbr.riskmanager.models.Problem;
import com.dancodingbr.riskmanager.repositories.ProblemsRepository;

@ExtendWith(MockitoExtension.class)
public class ProblemsServiceTest {

	private ProblemsService problemsService;

	@Mock
	private ProblemsRepository problemsRepository;

	@BeforeEach
	public void setUp() {
		problemsService = new ProblemsService(problemsRepository);
	}
	
	@Test
	public void it_should_returns_all_problems_when_get_all_problems() {
		// arrange
		List<Problem> expectedProblemsList = Arrays.asList(
				new Problem(1L, "BAD GRADES ON MATH"),
				new Problem(2L, "BODY WEIGHT RAISING"));
		
		given(problemsRepository.findAll()).willReturn(expectedProblemsList);
		
		// act
		List<Problem> actualProblemsList = this.problemsService.getAllProblems();
		
		// assert
		assertEquals(expectedProblemsList, actualProblemsList);
		
		// verify
		verify(problemsRepository).findAll();
	}

	@Test
	public void it_should_returns_nothing_when_saves_a_problem_successfully() throws Exception {
		
		// arrange
		Long id = 1L;
		String description = "BAD GRADES ON MATH";
		Problem problem = new Problem(id, description);

		given(problemsRepository.save(problem)).willReturn(problem);
		
		// act
		this.problemsService.save(problem);
		
		// assert
		assertTrue(problem instanceof Problem);
		
		// verify
		verify(problemsRepository).save(any(Problem.class));
	}
}
