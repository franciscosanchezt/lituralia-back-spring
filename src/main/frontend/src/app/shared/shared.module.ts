import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginDialogComponent} from "./dialog/login-dialog/login-dialog.component";
import {ConfirmDialogComponent} from "./dialog/confirm-dialog/confirm-dialog.component";
import {InfoDialogComponent} from "./dialog/info-dialog/info-dialog.component";
import {SignupDialogComponent} from "./dialog/signup-dialog/signup-dialog.component";
import {PasswordDialogComponent} from "./dialog/password-dialog/password-dialog.component";
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    LoginDialogComponent,
    PasswordDialogComponent,
    SignupDialogComponent,
    InfoDialogComponent,
    ConfirmDialogComponent
  ],
  imports: [
    MDBBootstrapModule.forRoot(),
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [
    CommonModule
  ]
})
export class SharedModule {
}
