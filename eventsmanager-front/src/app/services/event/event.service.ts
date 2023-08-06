import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { Observable, first } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Event } from 'src/app/models/Event';


@Injectable({
  providedIn: 'root'
})
export class EventService {

  constructor(private http: HttpClient) { }
  createEvent(event : Event | any): Observable<Event>{
     return this.http.post<Event>(`${environment.apiUrl+'/create'}`, event).pipe(first());  
  }
  getEvents():Observable<Event[]>{
    return this.http.get<Event[]>(`${environment.apiUrl}`).pipe(first()); 
  }

}
