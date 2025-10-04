import {ChangeDetectionStrategy, Component, ChangeDetectorRef} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { GroupToPackView } from '../../models/group-to-pack.model';
import { environment } from '../../../environments/environment'
import { CommonModule } from '@angular/common';
import { GroupToPackService } from '../../services/group-to-pack.service';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'group-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule],
  templateUrl: './group-list.component.html',
  styleUrl: './group-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GroupOverviewList {
  type: string = 'Group to pack';
  iconUrl: string = `${environment.host}/group-to-pack-icon.jpg`;

  groups: GroupToPackView[] = [];

  constructor(private service: GroupToPackService,
    private cdr: ChangeDetectorRef,
    private router: Router,
    private route: ActivatedRoute) {}

  ngOnInit() {
    this.getGroups();
  }

  onUpdate() {
    this.getGroups();
  }
  
  getGroups() {
    this.service.getGroups()
      .subscribe((groups: GroupToPackView[]) => { 
        this.groups = [...groups];
        this.cdr.markForCheck();
      });
      console.log("IS PAGE UPDATED");
  }

  deleteGroupById(id: number) {
    this.service.deleteGroupById(id).subscribe((msg: string) => {
      console.log(msg);
    })
    this.onUpdate();
  }

  viewGroup(id: number) {
    this.router.navigate([`/group/${id}`], { relativeTo: this.route });
  }
}
