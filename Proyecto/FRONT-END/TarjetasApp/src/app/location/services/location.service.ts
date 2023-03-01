import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError as observableThrowError } from 'rxjs';
import { aLocation } from 'src/app/package/interfaces/package-interface';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  

  private baseUrl: string = environment.baseUrl;
 
  constructor( private httpClient: HttpClient) { }

  listLocation(): Observable<aLocation[]> {
    const url = `${this.baseUrl}/locations`;
    return this.httpClient.get<aLocation[]>( url );
  }

  deleteLocation(idLocation: number): Observable<any> {
    return this.httpClient.delete<any>(`${ this.baseUrl }/locations/${ idLocation }`)
    .pipe(
      catchError( this.errorHandler)
    )
    
  }

  errorHandler( error: HttpErrorResponse){
    return observableThrowError(  "Status: " + error.error.status +" Mensaje:" +  error.error.errors.message );
  }
}
