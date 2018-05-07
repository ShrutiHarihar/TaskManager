import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { DataTableModule } from "angular2-datatable";

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { AssignComponent } from './assign.component';
import { AssignTaskRoutingModule } from './assign.routing';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    AssignTaskRoutingModule,
    ChartsModule,
    DataTableModule,
    BsDropdownModule.forRoot()
  ],
  declarations: [AssignComponent]
})
export class AssignTaskModule { }
