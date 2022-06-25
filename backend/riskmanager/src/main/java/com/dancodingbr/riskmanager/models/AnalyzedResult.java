package com.dancodingbr.riskmanager.models;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AnalyzedResult {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String problem;
	private String actionPlan;
	private String probabilityLevel;
	private String impactLevel;
	
	public AnalyzedResult() {}
	
	public AnalyzedResult(String problem, String actionPlan, String probabilityLevel, String impactLevel) {
		super();
		this.problem = problem;
		this.actionPlan = actionPlan;
		this.probabilityLevel = probabilityLevel;
		this.impactLevel = impactLevel;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getActionPlan() {
		return actionPlan;
	}

	public void setActionPlan(String actionPlan) {
		this.actionPlan = actionPlan;
	}

	public String getProbabilityLevel() {
		return probabilityLevel;
	}

	public void setProbabilityLevel(String probabilityLevel) {
		this.probabilityLevel = probabilityLevel;
	}

	public String getImpactLevel() {
		return impactLevel;
	}

	public void setImpactLevel(String impactLevel) {
		this.impactLevel = impactLevel;
	}

	public String getRiskLevel() {
		if (this.probabilityLevel.equals("RARE") && this.impactLevel.equals("HIGH")) {
			return "LOW";
		}
		else if (this.probabilityLevel.equals("INFREQUENT") && this.impactLevel.equals("MODERATE")) {
			return "LOW";
		}
		return null;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
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
		return Objects.equals(id, other.id);
	}

}
