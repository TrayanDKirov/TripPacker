import { ChangeDetectorRef, Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { PackingGroupResponseDto } from '../../../dtos/packing-group/packing-group-response.dto';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'packing-group',
  imports: [MatTableModule, MatIconModule, MatButtonModule, 
    MatCheckboxModule, FormsModule, MatCardModule,
    MatInputModule, MatFormFieldModule
  ],
  templateUrl: './packing-group-view.component.html',
  styleUrl: './packing-group-view.component.css'
})
export class PackingGroupView {
  displayedColumns: string[] = ["check-button", "name", "packed-quantity", "quantity-to-pack"]

  _packingGroup: PackingGroupResponseDto = {"id": 0, "name": "", "items": []};

  constructor(private cdr: ChangeDetectorRef) { }

  @Input() set packingGroup(packingGroup: PackingGroupResponseDto | undefined) {
    if (packingGroup != undefined) {
      this._packingGroup = packingGroup;
      console.log(this._packingGroup);
      this.cdr.markForCheck();
    }
  }

  get packingGroup() : PackingGroupResponseDto {
    return this._packingGroup;
  }
}
