import {NgModule} from '@angular/core';

import {CommonModule} from '@angular/common';

import {ChartsModule} from 'ng2-charts/ng2-charts';
import {DataTableModule} from 'angular2-datatable';

import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {FormsModule} from '@angular/forms';
import {ViewComponent} from './view.component';
import {ViewTaskRoutingModule} from './view.routing';
import {ModalModule} from 'ng2-bootstrap/modal';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ViewTaskRoutingModule,
    ChartsModule,
    DataTableModule,
    ModalModule.forRoot(),
    BsDropdownModule.forRoot()
  ],
  declarations: [ViewComponent],
  exports: [
    ModalModule
  ]
})
export class ViewTaskModule {
}
