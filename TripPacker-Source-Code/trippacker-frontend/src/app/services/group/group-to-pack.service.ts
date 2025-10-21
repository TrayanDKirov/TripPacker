import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { GroupToPackCreateDto } from '../../dtos/group/group-create.dto';
import { GroupToPackResponseDto } from '../../dtos/group/group-response.dto';
import { GroupToPackPreviewDto } from '../../dtos/group/group-preview.dto';

@Injectable({
  providedIn: 'root'
})
export class GroupToPackService {
  groupToPackUrl: string = `${environment.backendUrl}/group-to-pack`;

  constructor(private http: HttpClient) { }

  createGroup(createDto: GroupToPackCreateDto) : Observable<string> {
    console.log(`POST group to be called`);
    return this.http.post<string>(`${this.groupToPackUrl}`, createDto);
  }

  getGroups() : Observable<GroupToPackPreviewDto[]> {
    console.log("GET groups to be called");
    return this.http.get<GroupToPackPreviewDto[]>(`${this.groupToPackUrl}/all`);
  }

  getGroupById(id: number) : Observable<GroupToPackResponseDto> {
    console.log("GET group to be called");
    return this.http.get<GroupToPackResponseDto>(`${this.groupToPackUrl}/${id}`);
  }

  deleteGroupById(id: number) : Observable<string> {
    console.log("DELETE group to be called");
    return this.http.delete(`${this.groupToPackUrl}/${id}`, { responseType: 'text' });
  }
}
