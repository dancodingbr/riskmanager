import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable, of } from 'rxjs';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';

import { AnalyzedResultEditComponent } from './analyzed-result-edit.component';

class MockAnalyzedResultService {
  getRiskLevel(
    probabilityLevel: string,
    impactLevel: string
  ): Observable<string> {
    return of('LOW');
  }
}

describe('AnalyzedResultEditComponent', () => {
  let component: AnalyzedResultEditComponent;
  let fixture: ComponentFixture<AnalyzedResultEditComponent>;
  let analyzedResultService: AnalyzedResultService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnalyzedResultEditComponent],
      imports: [
        NoopAnimationsModule,
        ReactiveFormsModule,
        MatButtonModule,
        MatCardModule,
        MatInputModule,
        MatRadioModule,
        MatSelectModule,
      ],
      providers: [
        { provide: AnalyzedResultService, useClass: MockAnalyzedResultService },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyzedResultEditComponent);
    analyzedResultService = TestBed.inject(AnalyzedResultService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should sets risk level when on changing probability level', () => {
    // arrange
    component.analyzedResultEditForm.controls['problem'].setValue(
      'BAD GRADES ON MATH'
    );
    component.analyzedResultEditForm.controls['actionPlan'].setValue(
      'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'
    );
    component.analyzedResultEditForm.controls['probabilityLevel'].setValue('');
    component.analyzedResultEditForm.controls['impactLevel'].setValue('HIGH');
    const probabilityLevel = 'RARE';

    // act
    component.onChangeProbabilityLevel(probabilityLevel);

    // assert
    analyzedResultService
      .getRiskLevel(
        probabilityLevel,
        component.analyzedResultEditForm.controls['impactLevel'].value
      )
      .subscribe((riskLevel) => {
        expect(
          component.analyzedResultEditForm.controls['riskLevel'].value
        ).toBe(riskLevel);
      });
  });

  it('should sets risk level when on changing impact level', () => {
    // arrange
    component.analyzedResultEditForm.controls['problem'].setValue(
      'BAD GRADES ON MATH'
    );
    component.analyzedResultEditForm.controls['actionPlan'].setValue(
      'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'
    );
    component.analyzedResultEditForm.controls['probabilityLevel'].setValue(
      'RARE'
    );
    component.analyzedResultEditForm.controls['impactLevel'].setValue('');
    const impactLevel = 'HIGH';

    // act
    component.onChangeImpactLevel(impactLevel);

    // assert
    analyzedResultService
      .getRiskLevel(
        component.analyzedResultEditForm.controls['probabilityLevel'].value,
        impactLevel
      )
      .subscribe((riskLevel) => {
        expect(
          component.analyzedResultEditForm.controls['riskLevel'].value
        ).toBe(riskLevel);
      });
  });
});
