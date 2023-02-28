import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitud, Package } from '../interfaces/package-interface';
import { catchError } from 'rxjs/operators';
import { throwError as observableThrowError } from 'rxjs';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PackageService {

  date: Date = new Date();  
  private solicitud!: Solicitud;
 
  private baserUrl: string = environment.baseUrl;
  
  constructor( private httpClient: HttpClient ) {
   
   }

  listRequests( typeRequest: string ): Observable<Solicitud[]>{
    
    const url = `${this.baserUrl}/request/${ typeRequest }`;
    return this.httpClient.get<Solicitud[]>( url );
  }

  getListPackagesByIdRequest(idRequest: number): Observable<Package[]> {
    const url = `${ this.baserUrl}/packages/${ idRequest }`;
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
    const url = `${ this.baserUrl}/deliveries?idRequest=${ this.solicitud.idRequest }`;    
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
