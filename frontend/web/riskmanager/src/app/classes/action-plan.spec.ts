import { ActionPlan } from './action-plan';

describe('ActionPlan', () => {

  it('should create an instance with parameters', () => {
    // arrange
    const id = 1;
    const description = 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER';

    // act
    const actionPlan = new ActionPlan(
      id,
      description
    );

    // assert
    expect(actionPlan).toBeTruthy();
  });

});
