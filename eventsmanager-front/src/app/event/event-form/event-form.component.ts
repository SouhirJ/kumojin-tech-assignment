import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {FormBuilder} from '@angular/forms'
import { Router } from '@angular/router';
import { tap } from 'rxjs';
import { EventService } from 'src/app/services/event/event.service';

@Component({
  selector: 'app-event-form',
  templateUrl: './event-form.component.html',
  styleUrls: ['./event-form.component.css']
})
export class EventFormComponent implements OnInit{
  eventForm = this.fb.group({
    name:'',
    description :'',
    startDate:'',
    endDate:''
  });
 constructor(private fb: FormBuilder, private eventService: EventService, private router: Router, private datePipe: DatePipe){}

 ngOnInit(){}

 createEvent(){
    let startDate_ = this.datePipe.transform(this.eventForm.value['startDate'], "yyyy-MM-dd'T'HH:mm:ssZ");
    let endDate_= this.datePipe.transform(this.eventForm.value['endDate'], "yyyy-MM-dd'T'HH:mm:ssZ");
    
    this.eventForm.value['startDate'] = startDate_;
    this.eventForm.value['endDate'] = endDate_;

    this.eventService.createEvent(this.eventForm.value).pipe(
      tap(event => {
          this.router.navigate(['/events']);
    })).subscribe();
 }
}
