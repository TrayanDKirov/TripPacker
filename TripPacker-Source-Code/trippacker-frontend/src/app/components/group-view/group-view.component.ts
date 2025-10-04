import { ChangeDetectionStrategy, ChangeDetectorRef, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { GroupToPackResponseDto } from '../../dtos/group/group-response.dto';
import { GroupToPackService } from '../../services/group-to-pack.service';
import { MatCardModule } from '@angular/material/card';
import { ItemFormComponent } from '../item-to-pack/item-form/item-form.component';
import { ItemListComponent } from '../item-to-pack/item-list/item-list.component';

@Component({
  selector: 'app-group-view',
  imports: [CommonModule, MatCardModule, ItemFormComponent, ItemListComponent, RouterLink],
  templateUrl: './group-view.component.html',
  styleUrl: './group-view.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class GroupViewComponent {
  route = inject(ActivatedRoute);
  group: GroupToPackResponseDto | undefined;

  constructor(private service: GroupToPackService,
    private cdr: ChangeDetectorRef) {  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.getGroupById(id)
      .subscribe((group: GroupToPackResponseDto) => {
        this.group = group;
        this.cdr.markForCheck();
        console.log(`Group view is loaded ${this.group.id} ${this.group.name}`);
      })
  }
}
