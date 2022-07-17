import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
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

}
