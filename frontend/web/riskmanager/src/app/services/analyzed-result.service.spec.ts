import { TestBed } from '@angular/core/testing';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';
import { AnalyzedResultService } from './analyzed-result.service';
import { RiskLevelJson } from '../interfaces/risk-level-json';
import { AnalyzedResult } from '../classes/analyzed-result';
import { OperationStatusJson } from '../interfaces/operation-status-json';
import { HttpParams } from '@angular/common/http';
import { Problem } from '../classes/problem';

describe('AnalyzedResultService', () => {
  let httpTestingController: HttpTestingController;
  let service: AnalyzedResultService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    service = TestBed.inject(AnalyzedResultService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should returns http 200 response when GET a valid risk level given a probability and impact level', () => {
    // arrange
    const params = new HttpParams()
      .append('probabilityLevel', 'RARE')
      .append('impactLevel', 'HIGH');
    const expectedRiskLevel: RiskLevelJson = { risk_level: 'LOW' };

    // act
    service
      .getRiskLevel(
        params.get('probabilityLevel') as string,
        params.get('impactLevel') as string
      )
      .subscribe((actualRiskLevel) => {
        // assert: content
        expect(actualRiskLevel).toEqual(expectedRiskLevel.risk_level);
      });

    // asserts: http request call
    const url = `http://localhost:8080/riskmanager/api/analyzing-results/`;
    const request = httpTestingController.expectOne(
      (request) => request.url === url
    );
    expect(request.request.method).toEqual('GET');
    expect(request.request.params).toEqual(params);
    expect(request.request.responseType).toEqual('json');
    request.flush(expectedRiskLevel);
    httpTestingController.verify();
  });

  it('should returns http 200 response when POST analyzed result given a analyzed result', () => {
    // arrange
    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER';
    const probabilityLevel = 'RARE';
    const impactLevel = 'HIGH';
    const riskLevel = 'LOW';

    const analyzedResult = new AnalyzedResult(
      problem,
      actionPlan,
      probabilityLevel,
      impactLevel,
      riskLevel
    );

    const expectedOperationStatus: OperationStatusJson = {
      operation_status: 'SUCCESS',
    };

    // act
    service.save(analyzedResult).subscribe((actualOperationStatus) => {
      // assert: content
      expect(actualOperationStatus).toEqual(
        expectedOperationStatus.operation_status
      );
    });

    // asserts: http request call
    const url = `http://localhost:8080/riskmanager/api/analyzed-results/`;
    const request = httpTestingController.expectOne(
      (request) => request.url === url
    );
    expect(request.request.method).toEqual('POST');
    expect(request.request.body).toEqual(analyzedResult);
    expect(request.request.responseType).toEqual('json');
    request.flush(expectedOperationStatus);
    httpTestingController.verify();
  });

  it('should returns http 200 response when GET all analyzed results given a problem', () => {
    // arrange
    const params = new HttpParams()
      .append('problemId', 1);

    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER';
    const probabilityLevel = 'RARE';
    const impactLevel = 'HIGH';
    const riskLevel = 'LOW';
  
    const analyzedResult = new AnalyzedResult(
        problem,
        actionPlan,
        probabilityLevel,
        impactLevel,
        riskLevel
      );
  
    const expectedAnalyzedResults: AnalyzedResult[] = [
      analyzedResult
    ];

    // act
    service.getAllByProblem(
        params.get('problemId') as unknown as number,
      )
      .subscribe((actualAnalyzedResults) => {
        // assert: content
        expect(actualAnalyzedResults).toEqual(expectedAnalyzedResults);
      });

    // asserts: http request call
    const url = `http://localhost:8080/riskmanager/api/analyzed-results/`;
    const request = httpTestingController.expectOne(
      (request) => request.url === url
    );
    expect(request.request.method).toEqual('GET');
    expect(request.request.params).toEqual(params);
    expect(request.request.responseType).toEqual('json');
    request.flush(expectedAnalyzedResults);
    httpTestingController.verify();
  });

});
