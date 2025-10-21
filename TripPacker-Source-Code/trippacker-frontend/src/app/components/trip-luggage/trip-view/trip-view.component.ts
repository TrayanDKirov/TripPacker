import { ChangeDetectorRef, Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { TripLuggageService } from '../../../services/trip/trip-service';
import { TripLuggageResponseDto } from '../../../dtos/trip-luggage/trip-response.dto';
import { MatButtonModule } from '@angular/material/button';
import { PackingGroupView } from "../../packing-group/packing-group-view/packing-group-view.component";
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PackingItemResponseDto } from '../../../dtos/packing-item/packing-item-response.dto';
import { PackingItemService } from '../../../services/packing-item/packing-item.service';
import { PackingGroupResponseDto } from '../../../dtos/packing-group/packing-group-response.dto';

@Component({
  selector: 'app-trip-view',
  imports: [MatCardModule, MatButtonModule, RouterLink,
    PackingGroupView, CommonModule, FormsModule
  ],
  templateUrl: './trip-view.component.html',
  styleUrl: './trip-view.component.css'
})
export class TripViewComponent {
  originalTrip: TripLuggageResponseDto =
    {"name": "", "createdAt": "", "packingGroups": [], "packingItems": []};
  editableTrip: TripLuggageResponseDto = 
    {"name": "", "createdAt": "", "packingGroups": [], "packingItems": []};

  constructor(private service: TripLuggageService,
    private itemService: PackingItemService,
    private cdr: ChangeDetectorRef,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.getTripById(id)
      .subscribe(trip => {
        this.originalTrip = trip;
        this.editableTrip = structuredClone(this.originalTrip);

        this.cdr.markForCheck();
      });
  }

  private updateIfChanged(oldItem: PackingItemResponseDto, newItem: PackingItemResponseDto) {
    if (oldItem.isPacked != newItem.isPacked ||
      oldItem.packedQuantity != newItem.packedQuantity
    ) {
      console.log(`${newItem.name} ${oldItem.packedQuantity} != ${newItem.packedQuantity}`)
      this.itemService.updateItemById(newItem.id, 
        {"isPacked": newItem.isPacked, "packedQuantity": newItem.packedQuantity}).subscribe();
    }
  }

  onSubmit() {
    const originalGroups = this.originalTrip.packingGroups;
    const editableGroups = this.editableTrip.packingGroups;

    for (let i = 0; i < originalGroups.length; i++) {
      for (let j = 0; j < originalGroups[i].items.length; j++) {
        this.updateIfChanged(originalGroups[i].items[j], editableGroups[i].items[j]);
      }
    }
  }
}
