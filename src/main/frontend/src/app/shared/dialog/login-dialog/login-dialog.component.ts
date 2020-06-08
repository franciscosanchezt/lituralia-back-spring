import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MDBModalRef} from 'angular-bootstrap-md';
import {Subject} from 'rxjs';


@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  action: Subject<any> = new Subject();
  loginValidatingForm: FormGroup;
  errorText = '';

  constructor(public activeModal: MDBModalRef) {
  }

  ngOnInit(): void {
    this.loginValidatingForm = new FormGroup({
      loginFormModalEmail: new FormControl('', Validators.compose([Validators.required, Validators.minLength(5), Validators.email])),
      loginFormModalPassword: new FormControl('', Validators.compose([Validators.required, Validators.minLength(6)]))
    });
  }

  get loginFormModalEmail() {
    return this.loginValidatingForm.get('loginFormModalEmail');
  }

  get loginFormModalPassword() {
    return this.loginValidatingForm.get('loginFormModalPassword');
  }

  login() {

  }

}


