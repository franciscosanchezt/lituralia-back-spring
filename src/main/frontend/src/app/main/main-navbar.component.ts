import {Component, OnInit} from '@angular/core';
import {AuthService} from "./auth/auth.service";

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styles: []
})
export class MainNavbarComponent implements OnInit {

  constructor(public authService: AuthService) {
  }

  ngOnInit(): void {
  }

  logout() {
  }

  test() {

  }
}
