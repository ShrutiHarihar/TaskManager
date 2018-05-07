import { NgModule } from '@angular/core';
import { Routes,
  RouterModule } from '@angular/router';

import { AuthGuard } from '../../Guard/AuthGuard';
import {ViewComponent} from './view.component';

const routes: Routes = [
  {
    path: '',
    component: ViewComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewTaskRoutingModule {}
