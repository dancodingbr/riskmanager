import { AnalyzedResult } from './analyzed-result';
import { Problem } from './problem';

describe('AnalyzedResult', () => {
/*  it('should create an instance', () => {
    expect(new AnalyzedResult()).toBeTruthy();
  });*/

  it('should create an instance with parameters', () => {
    // arrange
    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER';
    const probabilityLevel = 'RARE';
    const impactLevel = 'HIGH';
    const riskLevel = 'LOW';

    // act
    const analyzedResult = new AnalyzedResult(
      problem,
      actionPlan,
      probabilityLevel,
      impactLevel,
      riskLevel
    );

    // assert
    expect(analyzedResult).toBeTruthy();
  });
});
