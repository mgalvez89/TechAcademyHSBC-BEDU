import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, RouterStateSnapshot, UrlSegment, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements  CanActivate{
  
  constructor( private authService: AuthService){}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {      
      if( this.authService.getUsuario1().id === this.authService.getId() ){        
        return true;
      }
      alert('Forbidden:\n¡NO cuentas con suficientes permisos para consultar este servicio!');
      console.log('Bloqueado por el AuthGuard - CanActivate');
      return false;
    }

  

    
    
    // canLoad(
    //   route: Route,
    //   segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    //     console.log('canLoad', false);
    //     console.log('route', route);
    //     console.log('segments', segments);
    //   return false;
    // }
}
