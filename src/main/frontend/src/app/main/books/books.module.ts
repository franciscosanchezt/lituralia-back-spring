import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BooksRoutingModule} from './books-routing.module';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {BooksHomeComponent} from "./books-home/books-home.component";
import {BooksListComponent} from './books-list/books-list.component';
import {FormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    BooksHomeComponent,
    BooksListComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    BooksRoutingModule,
    FormsModule
  ]
})
export class BooksModule {
}
