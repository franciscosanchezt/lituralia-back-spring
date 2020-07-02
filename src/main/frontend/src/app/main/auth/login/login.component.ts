import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  invalidLogin = false
  errorText = '';
  loginValidatingForm: FormGroup;

  constructor(private router: Router,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.loginValidatingForm = new FormGroup({
      loginFormModalUser: new FormControl('', Validators.compose([Validators.required, Validators.minLength(4)])),
      loginFormModalPassword: new FormControl('', Validators.compose([Validators.required, Validators.minLength(6)]))
    });
  }

  checkLogin() {
    if (!this.loginValidatingForm.invalid) {
      if (this.authService.authenticate(this.loginFormModalUser.value, this.loginFormModalPassword.value)
      ) {
        this.router.navigate([''])
        this.invalidLogin = false
        this.errorText = "login ok"
      } else
        this.invalidLogin = true
      this.errorText = "login err"
    }
  }

  get loginFormModalUser() {
    return this.loginValidatingForm.get('loginFormModalUser');
  }

  get loginFormModalPassword() {
    return this.loginValidatingForm.get('loginFormModalPassword');
  }
}
