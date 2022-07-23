export class AnalyzedResult {
    problem: string;
    actionPlan: string;
    probabilityLevel: string;
    impactLevel: string;
    riskLevel: string;

    constructor(
        problem: string,
        actionPlan: string,
        probabilityLevel: string,
        impactLevel: string,
        riskLevel: string
    ) {
        this.problem = problem;
        this.actionPlan = actionPlan;
        this.probabilityLevel = probabilityLevel;
        this.impactLevel = impactLevel;
        this.riskLevel = riskLevel;
    }
}
