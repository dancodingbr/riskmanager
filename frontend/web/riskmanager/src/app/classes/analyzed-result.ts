import { Problem } from "./problem";

export class AnalyzedResult {
    id!: number;
    problem: Problem;
    actionPlan: string;
    probabilityLevel: string;
    impactLevel: string;
    riskLevel: string;

    constructor(
        problem: Problem,
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
