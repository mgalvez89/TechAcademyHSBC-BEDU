import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import Swal from 'sweetalert2';

import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent {

  miFormulario: FormGroup = this.fb.group({
     email:    ['helen@mail.com', [ Validators.required, Validators.email ]],
     password: ['1234', [ Validators.required, Validators.minLength(4) ]],
  });

  constructor( private fb: FormBuilder,
               private router: Router,
               private authService: AuthService ){}

  login(){    
    //Para extraer los datos del formulario
    const { email, password } = this.miFormulario.value;    
    this.authService.login( email, password )
      .subscribe( user => {        
        Swal.fire({            
          icon: 'success',
          title: '¡Bienvenid@ ' + user.userName + '!',
          showConfirmButton: false,
          timer: 1500
        })        
        if ( user.ok === true ){
          this.router.navigateByUrl('/home');
        }else{          
          Swal.fire( 'Error', 'El correo o usuario no son validos, favor de verificar', 'error' );
        }
      });
    this.router.navigateByUrl('/home'); 
   }
}
