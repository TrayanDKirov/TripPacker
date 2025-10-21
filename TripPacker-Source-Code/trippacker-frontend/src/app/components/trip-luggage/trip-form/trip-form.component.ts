import { ChangeDetectorRef, Component } from '@angular/core';
import { TripLuggageCreateDto } from '../../../dtos/trip-luggage/trip-create.dto';
import { TripLuggageUpdateDto } from '../../../dtos/trip-luggage/trip-update.dto';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { MatButton, MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { TripLuggageService } from '../../../services/trip/trip-service';
import { GroupToPackPreviewDto } from '../../../dtos/group/group-preview.dto';
import { MatTableModule } from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { GroupToPackService } from '../../../services/group/group-to-pack.service';

export interface GroupsCheckListElement {
  isChecked: boolean;
  group: GroupToPackPreviewDto;
}

@Component({
  selector: 'trip-form',
  imports: [MatExpansionModule, MatInputModule, 
    MatFormFieldModule, MatButton, FormsModule, 
    MatTableModule, MatButtonModule,
    MatCheckboxModule],
  templateUrl: './trip-form.component.html',
  styleUrl: './trip-form.component.css'
})
export class TripFormComponent {
  displayedColumns: string[] = ["check-button", "name", "created-at"]
  groupCheckList: GroupsCheckListElement[] = [];

  createDto: TripLuggageCreateDto = {"name": ""};
  updateDto: TripLuggageUpdateDto = {"newGroup": null, "newGroups": null, "newName": null,};

  constructor(private groupService: GroupToPackService,
    private service: TripLuggageService,
    private cdr: ChangeDetectorRef) { }

  ngOnInit() {
    this.groupService.getGroups().subscribe((groups: GroupToPackPreviewDto[]) => {
      console.log("Groups should be recieved");
      this.groupCheckList = groups.map(g => ({ isChecked: false, group: g }));

      console.log("Table should be filled");
      console.log(this.groupCheckList[1].group.name);
      this.cdr.markForCheck();
    });
  }

  onSubmit() {
    this.updateDto.newGroups = this.groupCheckList
      .filter(element => (element.isChecked))
      .map(element => ({"groupToPackId": element.group.id}));

    this.service.createTrip(this.createDto).subscribe(response => {
      const locationHeader = response.headers.get('Location');
      if (locationHeader != undefined) {
        console.log(locationHeader);
        this.service.updateTripByLocation(locationHeader, this.updateDto).subscribe();
      }

      this.updateDto.newGroups = null;
    });
  }
}
