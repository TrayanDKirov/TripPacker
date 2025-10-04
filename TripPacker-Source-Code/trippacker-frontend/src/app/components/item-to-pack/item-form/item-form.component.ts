import { Component, Input } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ItemToPackCreateDto } from '../../../dtos/item-to-pack/item-create.dto';
import { ItemToPackService } from '../../../services/item-service/item.service';

@Component({
  selector: 'item-form',
  imports: [MatExpansionModule, MatInputModule, MatFormFieldModule, MatButton, FormsModule],
  templateUrl: './item-form.component.html',
  styleUrl: './item-form.component.css'
})
export class ItemFormComponent {
  @Input() groupId?: number;
  @Input() tripId?: number;
  @Input() createDto: ItemToPackCreateDto = 
    {"name": "", "quantityToPack": 0, "tripId": null, "groupId": null};

  constructor(private service: ItemToPackService) {  }

  onSubmit() {
    if (this.tripId != undefined) {
      this.createDto.tripId = this.tripId;
    }
    else if (this.groupId != undefined) {
      this.createDto.groupId = this.groupId;
    }

    console.log(`tripId ${this.tripId} groupId ${this.groupId}`);
    console.log(`createDto.name ${this.createDto.name} createDto.quantity ${this.createDto.quantityToPack} createDto.groupId ${this.createDto.groupId} createDto.tripId ${this.createDto.tripId}`);
    this.service.createItem(this.createDto).subscribe((msg: string) => {
      console.log(msg);
    });
  }
}
