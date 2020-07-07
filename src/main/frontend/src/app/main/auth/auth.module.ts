import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {AuthRoutingModule} from './auth-routing.module';
import {LoginComponent} from './login/login.component';
import {LogoutComponent} from './logout/logout.component';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {ReactiveFormsModule} from "@angular/forms";
import {ProfileComponent} from './profile/profile.component';


@NgModule({
  declarations: [
    LoginComponent,
    LogoutComponent,
    ProfileComponent],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MDBBootstrapModule.forRoot(),
    ReactiveFormsModule
  ]
})
export class AuthModule {
}
