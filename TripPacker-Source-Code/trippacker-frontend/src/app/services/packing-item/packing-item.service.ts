import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { PackingItemUpdateDto } from '../../dtos/packing-item/packing-item-update.dto';

@Injectable({
  providedIn: 'root'
})
export class PackingItemService {
  packingItemUrl: string = `${environment.backendUrl}/packing-item`;

  constructor(private http: HttpClient) { }

  updateItemById(id: number, updateDto: PackingItemUpdateDto) {
    return this.http.patch(`${this.packingItemUrl}/${id}`, updateDto);
  }
}
