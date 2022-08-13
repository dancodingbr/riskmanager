import { Problem } from './problem';

describe('Problem', () => {

  it('should create an instance with parameters', () => {
    // arrange
    const id = 1;
    const description = 'BAD GRADES ON MATH';

    // act
    const problem = new Problem(
      id,
      description
    );

    // assert
    expect(problem).toBeTruthy();
  });

});
