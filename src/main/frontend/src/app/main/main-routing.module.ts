import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainComponent} from "./main.component";
import {HomeModule} from "./home/home.module";
import {BooksModule} from "./books/books.module";


export function loadHomeModule() {
  return HomeModule
}

function loadBooksModule() {
  return BooksModule;
}

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', loadChildren: loadHomeModule},
      {path: 'books', loadChildren: loadBooksModule}
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule {
}
