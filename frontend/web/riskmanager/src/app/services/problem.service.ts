import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Problem } from '../classes/problem';

@Injectable({
  providedIn: 'root'
})
export class ProblemService {

  constructor(
    private httpClient: HttpClient
  ) { }

  getAllProblems(): Observable<Problem[]> {
    return this.httpClient.get<Problem[]>("http://localhost:8080/riskmanager/api/problems/");
  }
}
