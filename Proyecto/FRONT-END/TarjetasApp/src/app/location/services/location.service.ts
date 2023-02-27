import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { aLocation } from 'src/app/package/interfaces/package-interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private baserUrl: string = environment.baseUrl;
 
  constructor( private httpClient: HttpClient) { }

  listLocation(): Observable<aLocation[]> {
    const url = `${this.baserUrl}/locations`;
    return this.httpClient.get<aLocation[]>( url );
  }
}
