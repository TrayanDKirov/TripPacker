import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ItemToPackCreateDto } from '../../dtos/item-to-pack/item-create.dto';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { ItemToPackResponseDto } from '../../dtos/item-to-pack/item-response.dto';

@Injectable({
  providedIn: 'root'
})
export class ItemToPackService {
  itemUrl: string = `${environment.backendUrl}/item-to-pack`

  constructor(private http: HttpClient) { }

  createItem(item: ItemToPackCreateDto) : Observable<string> {
    console.log(`Item ${item.name} is to be created for group ${item.groupId} to ${this.itemUrl}`);
    return this.http.post<string>(this.itemUrl, item);
  }

  getItemsByGrouId(groupId: number) : Observable<ItemToPackResponseDto[]> {
    console.log(`Items of group with id ${groupId} to be requested`);
    return this.http.get<ItemToPackResponseDto[]>(`${this.itemUrl}/group/${groupId}`);
  }

  deleteItemById(id: number) : Observable<string> {
    return this.http.delete<string>(`${this.itemUrl}/${id}`);
  }
}
