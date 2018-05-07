import { NgModule } from '@angular/core';

import { CommonModule } from '@angular/common';

import { ChartsModule } from 'ng2-charts/ng2-charts';
import { DataTableModule } from "angular2-datatable";

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { CreateComponent } from './create.component';
import { CreateTaskRoutingModule } from './create.routing';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    CreateTaskRoutingModule,
    ChartsModule,
    DataTableModule,
    BsDropdownModule.forRoot()
  ],
  declarations: [CreateComponent]
})
export class CreateTaskModule { }
