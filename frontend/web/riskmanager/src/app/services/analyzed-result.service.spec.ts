import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AnalyzedResultService } from './analyzed-result.service';
import { RiskLevelJson } from '../interfaces/risk-level-json';

describe('AnalyzedResultService', () => {
  let httpTestingController: HttpTestingController;
  let service: AnalyzedResultService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
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

  it('should returns http 200 response when call http GET method given a probability and impact level', () => {
    // arrange
    const probabilityLevel = 'RARE';
    const impactLevel = 'HIGH';
    const expectedRiskLevel: RiskLevelJson = { risk_level: 'LOW' };

    // act
    service.getRiskLevel(
        probabilityLevel,
        impactLevel,
      ).subscribe((actualRiskLevel) => {
      // assert: content
      expect(actualRiskLevel).toEqual(expectedRiskLevel.risk_level)
    });

    // asserts: http request call
    const url = `http://localhost:8080/riskmanager/api/analyzing-results/`
    const request = httpTestingController.expectOne(
      (request) => request.url === url
    );
    expect(request.request.method).toEqual('GET');
    expect(request.request.responseType).toEqual('json');
    request.flush(expectedRiskLevel);
    httpTestingController.verify();
  });
});
