import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { environment } from '../../../../environments/environment';
import { TripLuggagePreviewDto } from '../../../dtos/trip-luggage/trip-preview.dto';
import { TripLuggageService } from '../../../services/trip/trip-service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'trip-list',
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './trip-list.component.html',
  styleUrl: './trip-list.component.css'
})
export class TripListComponent {
  type: string = "Trip Luggage";
  iconUrl: string = `${environment.imagesUrl}/trip-luggage-icon.png`;

  trips: TripLuggagePreviewDto[] = [];

  constructor(private service: TripLuggageService,
    private router: Router,
    private route: ActivatedRoute,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnInit() {
    this.getTrips();
  }

  getTrips() {
    this.service.getTrips().subscribe(trips => {
      this.trips = trips;
      this.cdr.markForCheck();
    })
  }

  viewTrip(id: number) {
    this.router.navigate([`/trip/${id}`], { relativeTo: this.route });
  }

  deleteGroupById(id: number) {
    this.service.deleteTripById(id).subscribe();
  }

  refresh() {
    this.getTrips();
  }
}