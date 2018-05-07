import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';
import { CreateComponent } from './create.component';
import { AuthGuard } from '../../Guard/AuthGuard';

const routes: Routes = [
  {
    path: '',
    component: CreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CreateTaskRoutingModule {}
