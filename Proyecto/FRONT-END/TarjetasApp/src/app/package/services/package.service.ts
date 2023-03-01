import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Solicitud, Package } from '../interfaces/package-interface';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class PackageService {
 
  private baserUrl: string = environment.baseUrl;
  
  constructor( private httpClient: HttpClient ) {
   
   }

  listRequests( typeRequest: string ): Observable<Solicitud[]>{
    
    const url = `${this.baserUrl}/request/${ typeRequest }`;
    return this.httpClient.get<Solicitud[]>( url );
  }

  getListPackagesByIdRequest(idRequest: number): Observable<Package[]> {
    const url = `${ this.baserUrl}/packages/location/${ idRequest }`;
    return this.httpClient.get<Package[]>( url );
  }
  
}
