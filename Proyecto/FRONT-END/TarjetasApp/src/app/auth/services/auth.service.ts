import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { AuthResponse, Usuario } from '../interfaces/auth.interface';
import { Observable, of, catchError } from 'rxjs';
import { map, tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baserUrl: string = environment.baseUrl;
  private _usuario!: Usuario | null | undefined;
  
  
  constructor( private http: HttpClient ){

  }
  
  login( email: string, password: string ){
    const url = `${ this.baserUrl }/auth/authenticate`;
    const body = { email, password};
    return this.http.post<AuthResponse>(url, body)
      .pipe( 
        tap( resp => {          
          if ( resp.ok ) {
            localStorage.setItem( 'token', resp.token! );                  
            this._usuario = {
              idUser:   resp.idUser!,
              userName: resp.userName!,
              ok:       resp.ok,
              rol:      resp.role!         
            }
          }
        }),
        map( resp => this._usuario ),
        catchError( err => of(err.message))
      );
  }

  get usuario(){
    // esta desestructurado
    return { ...this._usuario };
  }

  validarToken(): Observable<boolean> {
    const url = `${ this.baserUrl }/auth/validate`;             
    const headers = new HttpHeaders()
      .set( 'x-token', localStorage.getItem('token') || '' );

    return this.http.get<AuthResponse>( url, { headers } )
      .pipe(
        map( resp => {          
          localStorage.setItem( 'token', resp.token! );
            this._usuario = {
              idUser:   resp.idUser!,
              userName: resp.userName!,
              ok:       resp.ok,
              rol:      resp.role!         
            }

          return resp.ok;
        }),        
        catchError( error => of(false))        
      );
  }

  logout(){
    this._usuario = null;
    localStorage.clear();
  }


}
