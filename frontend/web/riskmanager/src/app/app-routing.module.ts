import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AnalyzedResultEditComponent } from './components/analyzed-result-edit/analyzed-result-edit.component';

const routes: Routes = [
  { path: '', component: AppComponent},
  { path: 'analyzed-results/:id', component: AnalyzedResultEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
