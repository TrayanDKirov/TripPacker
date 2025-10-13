import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { TripLuggageCreateDto } from '../../dtos/trip-luggage/trip-create.dto';
import { TripLuggageUpdateDto } from '../../dtos/trip-luggage/trip-update.dto';
import { Observable } from 'rxjs';
import { TripLuggageResponseDto } from '../../dtos/trip-luggage/trip-response.dto';
import { TripLuggagePreviewDto } from '../../dtos/trip-luggage/trip-preview.dto';

@Injectable({
  providedIn: 'root'
})
export class TripLuggageService {
  tripUrl: string = `${environment.backendUrl}/trip-luggage`

  constructor(private http: HttpClient) {  }

  createTrip(createDto: TripLuggageCreateDto) {
    return this.http.post(this.tripUrl, createDto, { observe: 'response' });
  }

  updateTripById(id: number, updateDto: TripLuggageUpdateDto) {
    return this.http.patch(`${this.tripUrl}/${id}`, updateDto);
  }

  updateTripByLocation(locationUrl: string, updateDto: TripLuggageUpdateDto) {
    console.log(`Patch request to send to ${environment.host}/${locationUrl}`);
    console.log(updateDto.newGroups);
    return this.http.patch(`${environment.host}/${locationUrl}`, updateDto);
  }

  getTripById(id: number) : Observable<TripLuggageResponseDto> {
    return this.http.get<TripLuggageResponseDto>(`${this.tripUrl}/${id}`);
  }

  getTrips() : Observable<TripLuggagePreviewDto[]> {
    return this.http.get<TripLuggagePreviewDto[]>(`${this.tripUrl}/all`);
  }

  deleteTripById(id: number) {
    console.log(`Delete is to be called to ${this.tripUrl}/${id}`);
    return this.http.delete(`${this.tripUrl}/${id}`);
  }
}
