import { ChangeDetectorRef, Component, ViewChild, signal } from '@angular/core';
import {MatExpansionModule} from '@angular/material/expansion';
import { GroupOverviewList } from '../group-list/group-list.component';
import { CommonModule } from '@angular/common';
import { GroupFormComponent } from "../group-form/group-form.component";
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'group-to-pack',
  imports: [CommonModule, 
    GroupOverviewList, GroupFormComponent],
  templateUrl: './group-to-pack.component.html',
  styleUrl: './group-to-pack.component.css'
})
export class GroupToPackComponent {
  readonly panelOpenState = signal(false);

  @ViewChild(GroupOverviewList) groups!: GroupOverviewList;
  @ViewChild(GroupFormComponent) form!: GroupFormComponent;
  router: Router;

  constructor(private cdr: ChangeDetectorRef, router: Router) { 
    this.router = router;
  }

  showGroups() {
    this.groups.getGroups();
    this.cdr.markForCheck();
  }
}
