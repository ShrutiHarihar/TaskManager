import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Layouts
import { FullLayoutComponent } from './layouts/full-layout.component';
import { AuthGuard } from './Guard/AuthGuard';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full',
  },
  {
    path: '',
    component: FullLayoutComponent,
    canActivate: [AuthGuard],
    children: [
      {
        path: 'dashboard',
        canActivateChild: [AuthGuard],
        loadChildren: './dashboard/dashboard.module#DashboardModule'
      },
      {
        path: 'createTask',
        canActivateChild: [AuthGuard],
        loadChildren: './task/create/create.module#CreateTaskModule'
      },
      {
        path: 'assignTask',
        canActivateChild: [AuthGuard],
        loadChildren: './task/assign/assign.module#AssignTaskModule'
      },
      {
        path: 'viewTask',
        canActivateChild: [AuthGuard],
        loadChildren: './task/view/view.module#ViewTaskModule'
      }
    ]
  },
  {
    path: 'login',
    pathMatch: 'full',
    component: LoginComponent
  },
  {
    path: 'register',
    pathMatch: 'full',
    component: RegisterComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
