import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AirQualityService {
  private apiUrl = 'http://localhost:8080/api/air-quality';

  constructor(private http: HttpClient) {}

  getAirQuality(city: string): Observable<any> {
    const apiUrl = `http://localhost:8080/api/air-quality/${city}`;
    return this.http.get(apiUrl); // Ensure the URL matches your backend endpoint
  }
}
