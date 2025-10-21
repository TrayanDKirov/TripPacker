import { Routes } from '@angular/router';
import { GroupViewComponent } from './components/group/group-view/group-view.component';
import { GroupToPackComponent } from './components/group/group-to-pack/group-to-pack.component';
import { HomeComponent } from './components/home/home.component';
import { TripLuggageComponent } from './components/trip-luggage/trip-luggage/trip-luggage.component';
import { TripViewComponent } from './components/trip-luggage/trip-view/trip-view.component';
import { RegisterComponent } from './components/user/register/register.component';
import { LoginComponent } from './components/user/login/login.component';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },

  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},

  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'groups', component: GroupToPackComponent, canActivate: [AuthGuard]},
  { path: 'group/:id', component: GroupViewComponent, canActivate: [AuthGuard]},
  { path: 'trips', component: TripLuggageComponent, canActivate: [AuthGuard]},
  { path: 'trip/:id', component: TripViewComponent, canActivate: [AuthGuard]},
  { path: '**', redirectTo: 'home', pathMatch: 'full'}
];
