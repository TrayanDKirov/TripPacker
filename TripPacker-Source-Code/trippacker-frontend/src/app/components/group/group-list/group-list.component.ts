import {ChangeDetectionStrategy, Component, ChangeDetectorRef} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { GroupToPackPreviewDto } from '../../../dtos/group/group-preview.dto';
import { environment } from '../../../../environments/environment'
import { CommonModule } from '@angular/common';
import { GroupToPackService } from '../../../services/group/group-to-pack.service'; 
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'group-list',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './group-list.component.html',
  styleUrl: './group-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GroupOverviewList {
  type: string = 'Group to pack';
  iconUrl: string = `${environment.imagesUrl}/group-to-pack-icon.jpg`;

  groups: GroupToPackPreviewDto[] = [];

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
      .subscribe((groups: GroupToPackPreviewDto[]) => { 
        this.groups = [...groups];
        this.cdr.markForCheck();
      });
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

  refresh() {
    this.getGroups()
  }
}
