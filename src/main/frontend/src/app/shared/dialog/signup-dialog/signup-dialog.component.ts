import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MDBModalRef} from 'angular-bootstrap-md';
import {Subject} from 'rxjs';

@Component({
  selector: 'app-signup-dialog',
  templateUrl: './signup-dialog.component.html',
  styleUrls: ['./signup-dialog.component.scss']
})
export class SignupDialogComponent implements OnInit {

  action: Subject<any> = new Subject();
  signupValidatingForm: FormGroup;
  signupError = '';

  constructor(public activeModal: MDBModalRef) {
  }

  ngOnInit(): void {
    this.signupValidatingForm = new FormGroup({
      signupFormModalName: new FormControl('', [Validators.required, Validators.minLength(1)]),
      signupFormModalEmail: new FormControl('', [Validators.required, Validators.minLength(5), Validators.email]),
      signupFormModalPassword: new FormControl('', [Validators.required, Validators.minLength(6), this.checkPasswordsTrigger.bind(this)]),
      signupFormModalPassword2: new FormControl('', [Validators.required, Validators.minLength(6), this.checkPasswords.bind(this)])
    });
  }

  checkPasswordsTrigger(control: FormControl) {
    if (this.signupValidatingForm) {
      this.signupValidatingForm.controls.signupFormModalPassword2.updateValueAndValidity();
    }
    return null;
  }

  checkPasswords(group: FormControl) { // here we have the 'passwords' group
    if (this.signupValidatingForm && group.value !== this.signupValidatingForm.controls.signupFormModalPassword.value) {
      return {passwordNotMatch: true};
    } else {
      return null;
    }
  }

  get signupFormModalName() {
    return this.signupValidatingForm.get('signupFormModalName');
  }

  get signupFormModalEmail() {
    return this.signupValidatingForm.get('signupFormModalEmail');
  }

  get signupFormModalPassword() {
    return this.signupValidatingForm.get('signupFormModalPassword');
  }

  get signupFormModalPassword2() {
    return this.signupValidatingForm.get('signupFormModalPassword2');
  }


  signup() {

  }
}
