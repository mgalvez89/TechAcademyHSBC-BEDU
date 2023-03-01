import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError as observableThrowError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExcelFileUploadService {

  private baseUrl: string = 'http://localhost:8080/api/v1';
  constructor( private http: HttpClient) {

   }

   public uploadfile( file: File, typeUrl: string, nameUser: string ){
    let url;
    if(typeUrl === 'locations'){
      url = `${ this.baseUrl }/${ typeUrl }/excel/upload`;
    }else{
      url = `${ this.baseUrl }/${ typeUrl }/excel/upload/${ nameUser }`;
    }    
    
    let formParams = new FormData;
    formParams.append("file", file);
    return this.http
               .post(url, formParams)
               .pipe( 
                  catchError( this.errorHandler )
               )
  }

  errorHandler( error: HttpErrorResponse){
    return observableThrowError(  "Status: " + error.error.status +" Mensaje:" +  error.error.errors.message );
  }
}
