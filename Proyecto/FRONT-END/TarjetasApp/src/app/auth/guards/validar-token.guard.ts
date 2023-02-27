import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ValidarTokenGuard implements CanActivate {

  constructor( private authService: AuthService,
               private router: Router){}

  canActivate(): Observable<boolean> | boolean {
      return this.authService.validarToken()
              .pipe(
                tap( valid => {
                  if ( !valid ){
                    this.router.navigateByUrl('/auth');
                  }
                })
              ); 
    }
  
}
