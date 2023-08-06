import { Component } from '@angular/core';
import { tap } from 'rxjs';
import { Event } from 'src/app/models/Event';
import { EventService } from 'src/app/services/event/event.service';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent {
  events : Event[] = [];
  constructor(private eventService: EventService){}

 ngOnInit(){
  this.getAllEvents();
 }

 getAllEvents(){
    this.eventService.getEvents().pipe(tap(events => this.events=events)).subscribe();
 }

}
