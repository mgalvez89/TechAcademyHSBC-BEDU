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

  // userIndefinido!: Auth;
  // userOperador: Auth | undefined;
  // userSupervisor!: Auth;
  // userAdmin!: Auth;
  // constructor( private router: Router,
  //              private authService: AuthService){
  //               this.userIndefinido = { id: "", email: "", usuario: "", rol:"" };
  //              }

  // login(id: string){

  //   this.authService.setId(id);
  //   //Ir al backend
  //   //Un usuario
  //     this.userOperador = this.authService.getUsuario1();
  //     this.userSupervisor = this.authService.getUsuario2();
  

  //     if(this.userOperador.id === id){
  //       this.authService.setUserLogueado(this.userOperador);
  //       this.authService.setUsuario2(this.userIndefinido);
  //       console.log(this.userOperador);
  //       localStorage.setItem('token', id);
  //       this.router.navigate(['./home']); 
  //     }
  //     else if(this.userSupervisor.id === id){
  //       this.authService.setUserLogueado(this.userSupervisor);
  //       this.authService.setUsuario1(this.userIndefinido);
  //       console.log(this.userSupervisor);
  //       localStorage.setItem('token', id);
  //       this.router.navigate(['./home']); 
  //     }
      
  //   }
    //Si todo sale bien me redirige a:
    // this.router.navigate(['./storagePackages']);


// get auth(){
// return this.authService.getUsuario1();
// }


// logout(){
// this.router.navigate(['./auth']);
// }

  miFormulario: FormGroup = this.fb.group({
     email:    ['helen@mail.com', [ Validators.required, Validators.email ]],
     password: ['1234', [ Validators.required, Validators.minLength(4) ]],
  });

  constructor( private fb: FormBuilder,
               private router: Router,
               private authService: AuthService ){}

  login(){
    console.log(this.miFormulario.value);
    //Para extraer los datos del formulario
    const { email, password } = this.miFormulario.value;    
    this.authService.login( email, password )
      .subscribe( ok => {        
        if ( ok === true ){
          this.router.navigateByUrl('/home');
        }else{
          console.log(ok);
          Swal.fire( 'Error', 'El correo o usuario no son validos', 'error' );
        }
      });
    this.router.navigateByUrl('/home'); 
   }
}
