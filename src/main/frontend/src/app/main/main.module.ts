import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {MainRoutingModule} from './main-routing.module';
import {MainComponent} from './main.component';
import {SharedModule} from "../shared/shared.module";
import {MainFooterComponent} from './main-footer.component';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {MainNavbarComponent} from './main-navbar.component';


@NgModule({
  declarations: [MainComponent, MainFooterComponent, MainNavbarComponent],
  imports: [
    SharedModule,
    CommonModule,
    MDBBootstrapModule.forRoot(),
    MainRoutingModule
  ]
})
export class MainModule {
}
