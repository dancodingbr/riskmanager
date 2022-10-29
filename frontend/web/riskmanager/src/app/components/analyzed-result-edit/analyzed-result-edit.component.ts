import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnalyzedResult } from 'src/app/classes/analyzed-result';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Problem } from 'src/app/classes/problem';
import { ActionPlan } from 'src/app/classes/action-plan';

@Component({
  selector: 'app-analyzed-result-edit',
  templateUrl: './analyzed-result-edit.component.html',
  styleUrls: ['./analyzed-result-edit.component.css'],
})
export class AnalyzedResultEditComponent implements OnInit {
  analyzedResultEditForm = this.fb.group({
    problem: this.fb.group({
      id: 1,
      description: 'BAD GRADES ON MATH'
    }),
    actionPlan: this.fb.group({
      id: 1,
      description: 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'
    }),
    probabilityLevel: '',
    impactLevel: '',
    riskLevel: '',
  });

  responseMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private analyzedResultService: AnalyzedResultService
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {}

  onChangeProbabilityLevel(probabilityLevel: string) {
    this.analyzedResultService
      .getRiskLevel(
        probabilityLevel,
        this.analyzedResultEditForm.controls['impactLevel'].value!
      )
      .subscribe((riskLevel) => {
        this.analyzedResultEditForm.controls['riskLevel'].setValue(riskLevel);
      });
  }

  onChangeImpactLevel(impactLevel: string) {
    this.analyzedResultService
      .getRiskLevel(
        this.analyzedResultEditForm.controls['probabilityLevel'].value!,
        impactLevel
      )
      .subscribe((riskLevel) => {
        this.analyzedResultEditForm.controls['riskLevel'].setValue(riskLevel);
      });
  }
  
  onClickSaveButton() {
    const problem = new Problem(
      this.analyzedResultEditForm.controls['problem'].value.id!,
      this.analyzedResultEditForm.controls['problem'].value.description!,
    );
    const actionPlan = new ActionPlan(
      this.analyzedResultEditForm.controls['actionPlan'].value.id!,
      this.analyzedResultEditForm.controls['actionPlan'].value.description!,
    );

    const analyzedResult = new AnalyzedResult(
      problem,
      actionPlan,
      this.analyzedResultEditForm.controls['probabilityLevel'].value!,
      this.analyzedResultEditForm.controls['impactLevel'].value!,
      this.analyzedResultEditForm.controls['riskLevel'].value!
    );

    this.analyzedResultService.save(analyzedResult).subscribe((operationStatus) => {
      this.responseMessage = 'The operation was a ' + operationStatus;
      this.snackBar.open(this.responseMessage, 'OK', {
        duration: 3000
      });
    });
  }

}
