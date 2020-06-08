import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {HomeRoutingModule} from './home-routing.module';
import {HomeComponent} from './home.component';
import {SharedModule} from "../../shared/shared.module";
import {MDBBootstrapModule} from "angular-bootstrap-md";


@NgModule({
  declarations: [
    HomeComponent
  ],
  imports: [
    SharedModule,
    CommonModule,
    MDBBootstrapModule.forRoot(),
    HomeRoutingModule
  ]
})
export class HomeModule {
}
