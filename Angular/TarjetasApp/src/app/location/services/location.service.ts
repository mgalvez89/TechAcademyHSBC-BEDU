import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { aLocation } from 'src/app/package/interfaces/package-interface';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  private urlBase: string = 'http://localhost:8080/api/v1';
 
  constructor( private httpClient: HttpClient) { }

  listLocation(): Observable<aLocation[]> {
    const url = `${this.urlBase}/locations`;
    return this.httpClient.get<aLocation[]>( url );
  }
}
