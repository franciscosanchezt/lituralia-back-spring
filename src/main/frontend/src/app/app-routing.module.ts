import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MainModule} from "./main/main.module";


function loadMainModule() {
  return MainModule
}

const routes: Routes = [
  {path: 'main', loadChildren: loadMainModule},
  {path: '', redirectTo: 'main', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule {
}
