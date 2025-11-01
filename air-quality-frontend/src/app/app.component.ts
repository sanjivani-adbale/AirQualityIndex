import { Component } from '@angular/core';
import { AirQualityService } from './air-quality.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  city: string = ''; 
  airQuality: any; 
  errorMessage: string = ''; 

  constructor(private airQualityService: AirQualityService) {}

  fetchAirQuality() {
    this.city = this.city.trim().toLowerCase();
  
    this.airQualityService.getAirQuality(this.city).subscribe(
      (data) => {
        if (data && data.city) {
          this.airQuality = data; 
          this.errorMessage = '';
        } else {
          this.errorMessage = `City "${this.city}" not found.`;
        }
      },
      (error) => {
        this.errorMessage = `City "${this.city}" not found.`;
        this.airQuality = null;
      }
    );
  }
  
}
