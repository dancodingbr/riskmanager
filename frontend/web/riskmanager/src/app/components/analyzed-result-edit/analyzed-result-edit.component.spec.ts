import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { Observable, of } from 'rxjs';
import { AnalyzedResult } from 'src/app/classes/analyzed-result';
import { Problem } from 'src/app/classes/problem';
import { ActionPlan } from 'src/app/classes/action-plan';
import { AnalyzedResultService } from 'src/app/services/analyzed-result.service';
import { Location } from '@angular/common';

import { AnalyzedResultEditComponent } from './analyzed-result-edit.component';

class MockAnalyzedResultService {
  getRiskLevel(
    probabilityLevel: string,
    impactLevel: string
  ): Observable<string> {
    return of('LOW');
  }

  save(
    analyzedResult: AnalyzedResult
  ): Observable<string> {
    return of('SUCCESS');
  }

}

class MockLocation {
  back(): void {}
}

describe('AnalyzedResultEditComponent', () => {
  let component: AnalyzedResultEditComponent;
  let fixture: ComponentFixture<AnalyzedResultEditComponent>;
  let analyzedResultService: AnalyzedResultService;
  let locationMock: Location;

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
        MatSnackBarModule,
      ],
      providers: [
        { provide: AnalyzedResultService, useClass: MockAnalyzedResultService },
        { provide: Location, useClass: MockLocation },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyzedResultEditComponent);
    analyzedResultService = TestBed.inject(AnalyzedResultService);
    locationMock = TestBed.inject(Location);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should sets risk level when on changing probability level', () => {
    // arrange
    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = new ActionPlan(1, 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
    component.analyzedResultEditForm.controls['problem'].setValue(
      problem
    );
    component.analyzedResultEditForm.controls['actionPlan'].setValue(
      actionPlan
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
        component.analyzedResultEditForm.controls['impactLevel'].value!
      )
      .subscribe((riskLevel) => {
        expect(
          component.analyzedResultEditForm.controls['riskLevel'].value
        ).toBe(riskLevel);
      });
  });

  it('should set risk level when on changing impact level', () => {
    // arrange
    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = new ActionPlan(1, 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
    component.analyzedResultEditForm.controls['problem'].setValue(
      problem
    );
    component.analyzedResultEditForm.controls['actionPlan'].setValue(
      actionPlan
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
        component.analyzedResultEditForm.controls['probabilityLevel'].value!,
        impactLevel
      )
      .subscribe((riskLevel) => {
        expect(
          component.analyzedResultEditForm.controls['riskLevel'].value
        ).toBe(riskLevel);
      });
  });

  it('should save the analyzed result when on clicking save button', () => {
    // arrange
    const problem = new Problem(1, 'BAD GRADES ON MATH');
    const actionPlan = new ActionPlan(1, 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER');
    const probabilityLevel = 'RARE';
    const impactLevel = 'HIGH';
    const riskLevel = 'LOW';

    const analyzedResult = new AnalyzedResult(
      problem,
      actionPlan,
      probabilityLevel,
      impactLevel,
      riskLevel
    );

    // act
    component.onClickSaveButton();

    // assert
    analyzedResultService.save(analyzedResult).subscribe((operationStatus) => {
      expect(component.responseMessage).toContain(
        operationStatus
      );
    });
  });

  it('should set the analyzed result properties when call on init method', () => {
    // arrange
    const analyzedResultFormExpected = {
      id: 1,
      problem: {
        id: 1,
        description: 'BAD GRADES ON MATH'
      },
      actionPlan: {
          id: 1,
          description: 'STUDY 8 HOURS PER WEEK ON NEXT SEMESTER'
      },
      probabilityLevel: 'RARE',
      impactLevel: 'HIGH',
      riskLevel: 'LOW'
    }

    history.pushState({data: analyzedResultFormExpected}, '')

    // act
    component.ngOnInit();

    // assert
    expect(component.analyzedResultEditForm.getRawValue()).toEqual(analyzedResultFormExpected);
  });

  it('should go back to the parent component when call on click back method', () => {
    // arrange
    spyOn(locationMock, 'back')

    // act
    component.onClickBack();

    // assert
    expect(locationMock.back).toHaveBeenCalledTimes(1);
  });

});
