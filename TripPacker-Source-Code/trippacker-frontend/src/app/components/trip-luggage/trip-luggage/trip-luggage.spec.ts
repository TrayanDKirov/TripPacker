import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripLuggage } from './trip-luggage.component';

describe('TripLuggage', () => {
  let component: TripLuggage;
  let fixture: ComponentFixture<TripLuggage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripLuggage]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TripLuggage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
