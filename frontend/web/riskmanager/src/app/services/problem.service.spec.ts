import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Problem } from '../classes/problem';

import { ProblemService } from './problem.service';

describe('ProblemService', () => {
  let httpTestingController: HttpTestingController;
  let service: ProblemService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(ProblemService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should returns http 200 response when GET all problems', () => {
    // arrange
    const expectedProblems: Problem[] = [
      new Problem(1, 'BAD GRADES ON MATH'),
      new Problem(2, 'BODY WEIGHT RAISING'),
    ];

    // act
    service.getAllProblems()
    .subscribe((actualProblems) => {
      // assert: content
      expect(actualProblems).toEqual(expectedProblems);
    });

    // asserts: http request call
    const url = `http://localhost:8080/riskmanager/api/problems/`;
    const request = httpTestingController.expectOne(
      (request) => request.url === url
    );
    expect(request.request.method).toEqual('GET');
    expect(request.request.responseType).toEqual('json');
    request.flush(expectedProblems);
    httpTestingController.verify();
  });

});
