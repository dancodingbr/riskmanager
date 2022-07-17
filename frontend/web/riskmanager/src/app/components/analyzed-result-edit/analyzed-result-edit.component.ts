import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';

@Component({
  selector: 'app-analyzed-result-edit',
  templateUrl: './analyzed-result-edit.component.html',
  styleUrls: ['./analyzed-result-edit.component.css'],
})
export class AnalyzedResultEditComponent implements OnInit {
  analyzedResultEditForm = this.fb.group({
    problem: 'BAD GRADES ON MATH',
    actionPlan: 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER',
    probabilityLevel: '',
    impactLevel: '',
    riskLevel: '',
  });

  constructor(
    private fb: FormBuilder,
    private analyzedResultService: AnalyzedResultService
  ) {}

  ngOnInit(): void {}

  onSubmit(): void {}

  onChangeProbabilityLevel(probabilityLevel: string) {
    this.analyzedResultService
      .getRiskLevel(
        probabilityLevel,
        this.analyzedResultEditForm.controls['impactLevel'].value
      )
      .subscribe((riskLevel) => {
        this.analyzedResultEditForm.controls['riskLevel'].setValue(riskLevel);
      });
  }

  onChangeImpactLevel(impactLevel: string) {
    this.analyzedResultService
      .getRiskLevel(
        this.analyzedResultEditForm.controls['probabilityLevel'].value,
        impactLevel
      )
      .subscribe((riskLevel) => {
        this.analyzedResultEditForm.controls['riskLevel'].setValue(riskLevel);
      });
  }
}
