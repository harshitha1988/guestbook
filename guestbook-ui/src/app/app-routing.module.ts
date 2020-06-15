import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FeebackComponent } from './components/feeback/feeback.component';


const routes: Routes = [
  {
    path : '',
    redirectTo: 'feedback',
    pathMatch: 'full'
  },

  {
    path : 'feedback',
    component : FeebackComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
