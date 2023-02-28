import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class ValidarTokenGuard implements CanActivate {

  

  constructor( private authService: AuthService,
               private router: Router){}
  
  canActivate(): Observable<boolean> | boolean 
  {

    if(localStorage.length === 1)
    {
      return this.authService.validarToken()
              .pipe(
                tap( valid => {
                  if ( !valid ){
                    this.authService.logout();
                    this.router.navigateByUrl('/auth');
                    Swal.fire({
                      title: '<strong>La sesión ha caducado</strong>',
                      icon: 'info',
                      html:'<b>Vuelve a iniciar sesión.</b>',
                      confirmButtonText:'Aceptar'
                    })  
                  }
                })
              ) 
    }else{
     return false;
    }
      
  }
  
}
