import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EventFormRoutingModule } from './event-form-routing.module';
import { EventFormComponent } from './event-form.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [EventFormComponent],
  exports: [EventFormComponent],
  imports: [
    CommonModule,
    EventFormRoutingModule,
    ReactiveFormsModule
  ]
})
export class EventFormModule { }
