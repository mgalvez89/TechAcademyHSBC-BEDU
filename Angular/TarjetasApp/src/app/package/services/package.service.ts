import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitud, Package } from '../interfaces/package-interface';
import { catchError } from 'rxjs/operators';
import { throwError as observableThrowError } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PackageService {

  date: Date = new Date();  
  private solicitud!: Solicitud;
 
  private urlBase: string = 'http://localhost:8080/api/v1';
  
  constructor( private httpClient: HttpClient ) {
   
   }

  listRequests( typeRequest: string ): Observable<Solicitud[]>{
    
    const url = `${this.urlBase}/request/${ typeRequest }`;
    return this.httpClient.get<Solicitud[]>( url );
  }

  getListPackagesByIdRequest(idRequest: number): Observable<Package[]> {
    const url = `${ this.urlBase}/packages/${ idRequest }`;
    return this.httpClient.get<Package[]>( url );
  }

  sendPackages( idRequest: number): Observable<Solicitud>{
    this.solicitud = {
      idRequest: idRequest,
      typeRequest: "DELIVERY",
      storageDate: this.date, 
      deliveryDate: null,
      requestDate: null,
      fileName: "archivo.xlsx",
      filePath: null,
      status: "SUBMITED"
    }
    const url = `${ this.urlBase}/deliveries?idRequest=${ this.solicitud.idRequest }`;
    console.log("url:" + url);
    return this.httpClient
               .put<Solicitud>(url, this.solicitud)
               .pipe(
                  catchError( this.errorHandler)
               )
  }

  errorHandler( error: HttpErrorResponse){
    return observableThrowError(  "Status: " + error.error.status +" Mensaje:" +  error.error.errors.message );
  }
  
}
