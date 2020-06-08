import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BooksRoutingModule} from './books-routing.module';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {BooksHomeComponent} from "./books-home/books-home.component";


@NgModule({
  declarations: [
    BooksHomeComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    BooksRoutingModule
  ]
})
export class BooksModule {
}
