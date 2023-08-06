import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventFormComponent } from './event-form.component';
import { EventService } from 'src/app/services/event/event.service';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

// Mock the EventService
const eventServiceMock = {
  createEvent: jasmine.createSpy('createEvent').and.returnValue(of({})),
};
describe('EventFormComponent', () => {
  let component: EventFormComponent;
  let fixture: ComponentFixture<EventFormComponent>;
  let eventService: EventService;
  let router: Router;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule, ReactiveFormsModule, RouterTestingModule],
      declarations: [EventFormComponent],
      providers: [{ provide: EventService, useValue: eventServiceMock }],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EventFormComponent);
    component = fixture.componentInstance;
    eventService = TestBed.inject(EventService);
    router = TestBed.inject(Router);
  });
  
  it('should create the component', () => {
    expect(component).toBeTruthy();
  });
  
  it('should call createEvent and navigate to /events', () => {
    const eventFormValue = {name : 'Yoga', description :'Yoga Event', startDate: '2023-10-05T08:20:10+05:30[Canada/Central]', endDate:'2023-10-06T08:20:10+05:30[Canada/Central]'};

    component.eventForm.setValue(eventFormValue);
    component.createEvent();
  
    expect(eventService.createEvent).toHaveBeenCalledWith(eventFormValue);
    expect(router.navigate).toHaveBeenCalledWith(['/events']);
  });
});
  
