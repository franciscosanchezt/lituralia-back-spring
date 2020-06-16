import {Component, OnInit} from '@angular/core';
import {DialogService} from "../shared/dialog/dialog.service";

@Component({
  selector: 'app-main-navbar',
  templateUrl: './main-navbar.component.html',
  styles: []
})
export class MainNavbarComponent implements OnInit {

  constructor(public dialogService: DialogService) {
  }

  ngOnInit(): void {
  }

  logout() {
  }

  test() {

  }
}
