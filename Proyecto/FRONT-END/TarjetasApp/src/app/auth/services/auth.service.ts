import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { AuthResponse, Usuario } from '../interfaces/auth.interface';
import { Observable, of, catchError } from 'rxjs';
import { map, tap } from 'rxjs/operators'
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

/*   private id: string = '';
  private usuario1!: Auth;
  private usuario2!: Auth;
  private userLogueado!: Auth;

  constructor( private http: HttpClient ) {
    this.usuario1 = { id: "1", email: "mperez01@correo.com", usuario: "mGarcia01", rol:"operador" };
    this.usuario2 = { id: "2", email: "gValencia02@correo.com", usuario: "gRobles02", rol:"supervisor" };
    this.userLogueado = { id: "0", email: "", usuario: "", rol:"" };
  }

  getId(){
    return this.id;
  }

  setId(id:string){
    this.id=id;
  }

  getUsuario1(){
    return this.usuario1;
  }

  setUsuario1( usuario1: Auth ){
    this.usuario1 = usuario1;
  }

  getUsuario2(){
    return this.usuario2;
  }

  setUsuario2( usuario2: Auth ){
    this.usuario2 = usuario2;
  }

  getUserLogueado(){
    return this.userLogueado;
  }

  setUserLogueado(userLogueado: Auth){
    this.userLogueado = userLogueado;
  }

  verificaAutenticacion(): Observable<boolean> | boolean{
    if( !localStorage.getItem('token')){
      // con la forma de Observable
      // return of(false);
      return false;
    }
    // con la forma de Observable
    // return of(true);
    return true;
  } */

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
        map( resp => resp.ok ),
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
        catchError( error => of(false) )
      );
  }

  logout(){
    this._usuario = null;
    localStorage.clear();
  }

}
