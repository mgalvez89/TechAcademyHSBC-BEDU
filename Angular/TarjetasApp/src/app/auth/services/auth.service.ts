import { Injectable } from '@angular/core';
import { Auth } from '../interfaces/auth.interface';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private id: string = '';
  private usuario1!: Auth;
  private usuario2!: Auth;
  private userLogueado!: Auth;

  constructor() {
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
  }

}
