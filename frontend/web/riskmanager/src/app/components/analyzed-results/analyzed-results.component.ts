import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTable } from '@angular/material/table';
import { AnalyzedResult } from 'src/app/classes/analyzed-result';
import { Problem } from 'src/app/classes/problem';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';
import { ProblemService } from 'src/app/services/problem.service';
import { AnalyzedResultsDataSource } from './analyzed-results-datasource';

@Component({
  selector: 'app-analyzed-results',
  templateUrl: './analyzed-results.component.html',
  styleUrls: ['./analyzed-results.component.css']
})
export class AnalyzedResultsComponent implements AfterViewInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  @ViewChild(MatTable) table!: MatTable<AnalyzedResult>;
  dataSource: AnalyzedResultsDataSource;

  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns = ['id', 'actionPlan', 'riskLevel'];

  problems: Problem[] = [];

  problemSelected!: Problem;

  constructor(
    private analyzedResultService: AnalyzedResultService,
    private problemService: ProblemService,
  ) {
    this.dataSource = new AnalyzedResultsDataSource();
  }

  ngAfterViewInit(): void {
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.table.dataSource = this.dataSource;

    this.problemService
      .getAllProblems()
      .subscribe((problems) => {
        this.problems = [...problems]
      });
  }

  onChangeProblem(id: number) {
    this.analyzedResultService
      .getAllByProblem(id)
      .subscribe((analyzedResults) => {
        this.dataSource.data = [...analyzedResults];
      });
  }

}
