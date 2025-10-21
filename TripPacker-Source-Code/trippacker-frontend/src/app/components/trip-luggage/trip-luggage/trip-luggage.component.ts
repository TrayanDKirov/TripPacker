import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterLink } from '@angular/router';
import { TripFormComponent } from '../trip-form/trip-form.component';
import { TripListComponent } from "../trip-list/trip-list.component";

@Component({
  selector: 'trip-luggage',
  imports: [RouterLink, MatIconModule, MatButtonModule, TripFormComponent, TripListComponent],
  templateUrl: './trip-luggage.component.html',
  styleUrl: './trip-luggage.component.css'
})
export class TripLuggageComponent {

}
