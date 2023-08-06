import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path:'events', loadChildren:()=>import('./event/event-list/event-list.module').then(mod=>mod.EventListModule)
  },
  {
    path:'form', loadChildren:()=>import('./event/event-form/event-form.module').then(mod=>mod.EventFormModule)
  },
  {
    path:'', 
    pathMatch:'full',
    redirectTo:'events'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
