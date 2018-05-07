import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { DataTableModule } from "angular2-datatable";

import { DashboardComponent } from './dashboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FormsModule } from '@angular/forms';
import { ModalModule } from 'ng2-bootstrap/modal';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    DashboardRoutingModule,
    ChartsModule,
    DataTableModule,
    ModalModule.forRoot(),
    BsDropdownModule.forRoot()
  ],
  declarations: [DashboardComponent],
  exports: [
    ModalModule
  ]
})
export class DashboardModule { }
