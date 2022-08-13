import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';

import { AnalyzedResultsComponent } from './analyzed-results.component';
import { Problem } from 'src/app/classes/problem';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';
import { Observable, of } from 'rxjs';
import { AnalyzedResult } from 'src/app/classes/analyzed-result';
import { ProblemService } from 'src/app/services/problem.service';

class MockAnalyzedResultService {
  getAllByProblem(problemId: number): Observable<AnalyzedResult[]> {
    return of([]);
  }
}

class MockProblemService {
  getAllProblems(): Observable<Problem[]> {
    return of([]);
  }
}

describe('AnalyzedResultsComponent', () => {
  let component: AnalyzedResultsComponent;
  let fixture: ComponentFixture<AnalyzedResultsComponent>;
  let analyzedResultService: AnalyzedResultService;
  let problemService: ProblemService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnalyzedResultsComponent],
      imports: [
        NoopAnimationsModule,
        MatPaginatorModule,
        MatSortModule,
        MatTableModule,
      ],
      providers: [
        { provide: AnalyzedResultService, useClass: MockAnalyzedResultService },
        { provide: ProblemService, useClass: MockProblemService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyzedResultsComponent);
    analyzedResultService = TestBed.inject(AnalyzedResultService);
    problemService = TestBed.inject(ProblemService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should compile', () => {
    expect(component).toBeTruthy();
  });

  it('should get analyzed results when on changing problem', () => {
    // arrange
    const id = 1;
    const description = 'BAD GRADES ON MATH';
    const problem = new Problem(id, description);
    component.problems.push(problem);

    // act
    component.onChangeProblem(problem.id);

    // assert
    analyzedResultService
      .getAllByProblem(component.problemSelected?.id)
      .subscribe((analyzedResults) => {
        expect(component.dataSource.data).toEqual(analyzedResults);
      });
  });

  it('should get problems list when after init components view', () => {
    // arrange
    component.problems = [];

    // act
    component.ngAfterViewInit();

    // assert
    problemService
      .getAllProblems()
      .subscribe((problems) => {
        expect(component.problems).toEqual(problems);
      });
  });

});
