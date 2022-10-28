import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, of } from 'rxjs';
import { AnalyzedResult } from '../classes/analyzed-result';
import { OperationStatusJson } from '../interfaces/operation-status-json';
import { RiskLevelJson } from '../interfaces/risk-level-json';

@Injectable({
  providedIn: 'root'
})
export class AnalyzedResultService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getRiskLevel(
    probabilityLevel: string,
    impactLevel: string
  ): Observable<string> {
    const params = new HttpParams()
      .append('probabilityLevel', probabilityLevel)
      .append('impactLevel', impactLevel)
    return this.httpClient.get<RiskLevelJson>("http://localhost:8080/riskmanager/api/analyzing-results/", {params: params}).pipe(
      map(data => data.risk_level)
    );
  }

  save(analyzedResult: AnalyzedResult): Observable<string> {
    return this.httpClient.post<OperationStatusJson>("http://localhost:8080/riskmanager/api/analyzed-result/", analyzedResult).pipe(
      map(data => data.operation_status)
    );
  }

  getAllByProblem(problemId: number): Observable<AnalyzedResult[]> {
    const params = new HttpParams()
      .append('problemId', problemId)
    return this.httpClient.get<AnalyzedResult[]>("http://localhost:8080/riskmanager/api/analyzed-results/", {params: params});
  }
}
