import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {BooksRoutingModule} from './books-routing.module';
import {MDBBootstrapModule} from "angular-bootstrap-md";
import {BooksHomeComponent} from "./books-home/books-home.component";
import {BooksListComponent} from './books-list/books-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BooksDetailComponent} from './books-detail/books-detail.component';


@NgModule({
  declarations: [
    BooksHomeComponent,
    BooksListComponent,
    BooksDetailComponent
  ],
  imports: [
    CommonModule,
    MDBBootstrapModule.forRoot(),
    BooksRoutingModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class BooksModule {
}
