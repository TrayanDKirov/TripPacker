import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [MatIconModule, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  constructor(private router: Router,
    private route: ActivatedRoute
  ) { }

  navigateToGroups() {
    this.router.navigate([`/groups`], { relativeTo: this.route });
  }

  navigateToTrips() {
    this.router.navigate([`/trips`], { relativeTo: this.route });
  }
}
