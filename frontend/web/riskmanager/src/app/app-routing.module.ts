import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnalyzedResultEditComponent } from './components/analyzed-result-edit/analyzed-result-edit.component';
import { AnalyzedResultsComponent } from './components/analyzed-results/analyzed-results.component';

const routes: Routes = [
  { path: '', redirectTo: '/analyzed-results', pathMatch: 'full'},
  { path: 'analyzed-results', component: AnalyzedResultsComponent},
  { path: 'analyzed-results/:id', component: AnalyzedResultEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
