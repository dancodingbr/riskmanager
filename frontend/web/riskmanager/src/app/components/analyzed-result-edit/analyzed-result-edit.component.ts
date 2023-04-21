import { AfterViewInit, Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnalyzedResult } from 'src/app/classes/analyzed-result';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Problem } from 'src/app/classes/problem';
import { ActionPlan } from 'src/app/classes/action-plan';
import { Location } from '@angular/common';

@Component({
  selector: 'app-analyzed-result-edit',
  templateUrl: './analyzed-result-edit.component.html',
  styleUrls: ['./analyzed-result-edit.component.css'],
})
export class AnalyzedResultEditComponent implements OnInit, AfterViewInit {

  analyzedResultEditForm = this.fb.group({
    id: 0,
    problem: this.fb.group({
      id: 0,
      description: ''
    }),
    actionPlan: this.fb.group({
      id: 0,
      description: ''
    }),
    probabilityLevel: '',
    impactLevel: '',
    riskLevel: '',
  });

  responseMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private location: Location,
    private analyzedResultService: AnalyzedResultService
  ) {}

  ngOnInit(): void {
    if (history.state?.data !== undefined) {
      this.analyzedResultEditForm.setValue(history.state?.data)
    }
  }
  
  ngAfterViewInit() {}

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

  onClickBack() {
    this.location.back();
  }

}
