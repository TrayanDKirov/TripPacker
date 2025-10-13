import { Routes } from '@angular/router';
import { GroupViewComponent } from './components/group/group-view/group-view.component';
import { GroupToPackComponent } from './components/group/group-to-pack/group-to-pack.component';
import { HomeComponent } from './components/home/home.component';
import { TripLuggageComponent } from './components/trip-luggage/trip-luggage/trip-luggage.component';
import { TripViewComponent } from './components/trip-luggage/trip-view/trip-view.component';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent},
  { path: 'groups', component: GroupToPackComponent},
  { path: 'group/:id', component: GroupViewComponent},
  { path: 'trips', component: TripLuggageComponent},
  { path: 'trip/:id', component: TripViewComponent},
  { path: '**', redirectTo: 'home', pathMatch: 'full' }
];
