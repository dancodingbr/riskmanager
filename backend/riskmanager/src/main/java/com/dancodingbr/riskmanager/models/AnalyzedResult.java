package com.dancodingbr.riskmanager.models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dancodingbr.riskmanager.enums.ImpactLevel;
import com.dancodingbr.riskmanager.enums.ProbabilityLevel;
import com.dancodingbr.riskmanager.enums.RiskLevel;

@Entity
@Table(name = "analyzed_result")
public class AnalyzedResult implements Serializable {

	private static final long serialVersionUID = 2798554009379643370L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "problem_id")
	private Problem problem;

	private String actionPlan;

	@Enumerated(EnumType.STRING)
	private ProbabilityLevel probabilityLevel;

	@Enumerated(EnumType.STRING)
	private ImpactLevel impactLevel;

	@Enumerated(EnumType.STRING)
	private RiskLevel riskLevel;
	
	public AnalyzedResult() {}

	public AnalyzedResult(Problem problem, String actionPlan, ProbabilityLevel probabilityLevel, ImpactLevel impactLevel,
			RiskLevel riskLevel) {
		this.problem = problem;
		this.actionPlan = actionPlan; 
		this.probabilityLevel = probabilityLevel; 
		this.impactLevel = impactLevel;
		this.riskLevel = riskLevel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public ProbabilityLevel getProbabilityLevel() {
		return probabilityLevel;
	}

	public void setProbabilityLevel(ProbabilityLevel probabilityLevel) {
		this.probabilityLevel = probabilityLevel;
	}

	public ImpactLevel getImpactLevel() {
		return impactLevel;
	}

	public void setImpactLevel(ImpactLevel impactLevel) {
		this.impactLevel = impactLevel;
	}

	public RiskLevel getRiskLevel() {
		return riskLevel;
	}

	public void setRiskLevel(RiskLevel riskLevel) {
		this.riskLevel = riskLevel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(actionPlan, id, impactLevel, probabilityLevel, problem, riskLevel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalyzedResult other = (AnalyzedResult) obj;
		return Objects.equals(actionPlan, other.actionPlan) && Objects.equals(id, other.id)
				&& impactLevel == other.impactLevel && probabilityLevel == other.probabilityLevel
				&& Objects.equals(problem, other.problem) && riskLevel == other.riskLevel;
	}
	
}
