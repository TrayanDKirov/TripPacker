import { Component, Input } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { GroupToPackCreateDto } from '../../dtos/group/group-create.dto';
import { FormsModule } from '@angular/forms';
import { MatButton } from '@angular/material/button';
import { GroupToPackService } from '../../services/group-to-pack.service';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'group-form',
  imports: [MatExpansionModule, MatInputModule, MatFormFieldModule, MatButton, FormsModule],
  templateUrl: './group-form.component.html',
  styleUrl: './group-form.component.css'
})
export class GroupFormComponent {
  @Input() createDto: GroupToPackCreateDto = {"name": ""};

  constructor(private service: GroupToPackService) { }

  onSubmit() {
    this.service.createGroup(this.createDto).subscribe((location: string) => {
      console.log(location);
    });
    console.log(`Group ${this.createDto.name} was created`);
  }
}
