import { Routes } from '@angular/router';
import { GroupViewComponent } from './components/group-view/group-view.component';
import { GroupToPackComponent } from './components/group-to-pack/group-to-pack.component';

export const routes: Routes = [
  { path: '', redirectTo: 'groups', pathMatch: 'full' },
  { path: 'groups', component: GroupToPackComponent},
  { path: 'group/:id', component: GroupViewComponent},
  { path: '**', redirectTo: '', pathMatch: 'full' }
];
