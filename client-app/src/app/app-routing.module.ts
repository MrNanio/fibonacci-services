import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DocuComponent} from "./docu/docu.component";
import {CalcComponent} from "./calc/calc.component";
import {AboutComponent} from "./about/about.component";

const routes: Routes = [
  {path: "documentation", component: DocuComponent},
  {path: "home", component: AboutComponent},
  {path: "calculator", component: CalcComponent},
  {path: "", redirectTo: "home", pathMatch: "full"},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
