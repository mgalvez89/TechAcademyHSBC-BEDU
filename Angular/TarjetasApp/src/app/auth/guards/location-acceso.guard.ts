import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LocationAccesoGuard implements CanActivate {

  constructor( private authService: AuthService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {      
      if( this.authService.getUsuario2().id === this.authService.getId() ){
        return true;
      }
      alert('Forbidden:\n¡NO cuentas con suficientes permisos para consultar este servicio!');
      console.log('Bloqueado por el AuthGuard - CanActivate');
      return false;
    }
  
}
