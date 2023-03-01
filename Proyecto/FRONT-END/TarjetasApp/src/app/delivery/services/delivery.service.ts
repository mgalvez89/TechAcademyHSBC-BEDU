import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { throwError as observableThrowError } from 'rxjs';
import { Solicitud, uPackage } from 'src/app/package/interfaces/package-interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class DeliveryService {
  date: Date = new Date();  
  private solicitud!: Solicitud;

  private baserUrl: string = environment.baseUrl;
  
  constructor( private httpClient: HttpClient ) {}   

   getListPackagesBranchByIdRequest(idRequest: number): Observable<uPackage[]> {
    const url = `${ this.baserUrl}/packages/branch/${ idRequest }`;
    return this.httpClient.get<uPackage[]>( url );
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
