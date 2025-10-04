import { Component } from '@angular/core';
import { GroupToPackComponent } from "./components/group-to-pack/group-to-pack.component";
import { environment } from '../environments/environment';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  logoUrl: string = `${environment.host}/logo.png`;
  
  protected title = 'trippacker-frontend';
}
