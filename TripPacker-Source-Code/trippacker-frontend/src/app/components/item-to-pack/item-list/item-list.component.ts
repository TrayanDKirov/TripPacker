import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input } from '@angular/core';
import {MatTableModule} from '@angular/material/table';
import { ItemToPackResponseDto } from '../../../dtos/item-to-pack/item-response.dto';
import { MatButtonModule } from '@angular/material/button';
import { MatIcon } from '@angular/material/icon';
import { ItemToPackService } from '../../../services/item-service/item.service';

@Component({
  selector: 'item-list',
  imports: [MatTableModule, MatIcon, MatButtonModule],
  templateUrl: './item-list.component.html',
  styleUrl: './item-list.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ItemListComponent {
  displayedColumns: string[] = ["name", "quantity-to-pack", "delete-button"]

  @Input() groupId?: number;
  @Input() tripId?: number;
  private _items: ItemToPackResponseDto[] = [];

  constructor(private service: ItemToPackService,
    private cdr: ChangeDetectorRef) { }

  @Input() set items(items: ItemToPackResponseDto[] | undefined) {
    if (items != undefined) {
      this._items = items;
    }
  }

  get items() : ItemToPackResponseDto[] {
    return this._items;
  }

  refresh() {
    console.log("Refresh is called")
    if (this.groupId != undefined) {
      this.service.getItemsByGrouId(this.groupId).subscribe((items: ItemToPackResponseDto[]) => {
        this.items = items;
        this.cdr.markForCheck();
        console.log("Items should be refreshed. ");
      });
      return;
    }
    console.log("GroupId is undefiend");
  }

  deleteItemById(id: number) {
    console.log(`Item witn id ${id} is to be deleted`)
    this.service.deleteItemById(id).subscribe((msg: string) => {
      console.log(msg);
    });
  }
}
