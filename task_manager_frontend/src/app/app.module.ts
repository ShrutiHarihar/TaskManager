import { NgModule } from '@angular/core';
import { CommonModule, PathLocationStrategy } from '@angular/common';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule, Http, RequestOptions, XHRBackend } from '@angular/http';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';

import { AppComponent } from './app.component';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { NAV_DROPDOWN_DIRECTIVES } from './shared/nav-dropdown.directive';
import { DataTableModule } from "angular2-datatable";

import { SIDEBAR_TOGGLE_DIRECTIVES } from './shared/sidebar.directive';

import { AppRoutingModule } from './app.routing';

import { FullLayoutComponent } from './layouts/full-layout.component';
import { DashboardModule } from './dashboard/dashboard.module';
import { AuthenticationService } from './services/authentication.service';
import { TaskService } from './services/task.service';
import { AuthGuard } from './Guard/AuthGuard';
import { RoleGuard } from './Guard/RoleGuard';
import { CreateTaskModule } from './task/create/create.module';
import { AssignTaskModule } from './task/assign/assign.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ViewTaskModule } from './task/view/view.module';


@NgModule({
  imports: [
    CommonModule,
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    TabsModule.forRoot(),
    DashboardModule,
    CreateTaskModule,
    AssignTaskModule,
    ViewTaskModule
  ],
  declarations: [
    AppComponent,
    FullLayoutComponent,
    NAV_DROPDOWN_DIRECTIVES,
    SIDEBAR_TOGGLE_DIRECTIVES,
    LoginComponent,
    RegisterComponent
  ],
  providers: [
    AuthGuard,
    RoleGuard,
    AuthenticationService,
    TaskService,
    {
    provide: LocationStrategy,
    useClass: PathLocationStrategy
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
