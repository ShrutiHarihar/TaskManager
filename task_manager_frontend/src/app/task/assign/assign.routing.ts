import { NgModule } from '@angular/core';
import { Routes,
     RouterModule } from '@angular/router';
import { AssignComponent } from './assign.component';
import { RoleGuard } from '../../Guard/RoleGuard';

const routes: Routes = [
  {
    path: '',
    component: AssignComponent,
    data: {
      expectedRole: ['Manager'],
    },
    canActivate: [RoleGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AssignTaskRoutingModule {}
