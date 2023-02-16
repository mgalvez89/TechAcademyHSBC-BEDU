import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from '../../interfaces/auth.interface';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent {

  userIndefinido!: Auth;
  userOperador: Auth | undefined;
  userSupervisor!: Auth;
  userAdmin!: Auth;
  constructor( private router: Router,
               private authService: AuthService){
                this.userIndefinido = { id: "", email: "", usuario: "", rol:"" };
               }

  login(id: string){

    this.authService.setId(id);
    //Ir al backend
    //Un usuario
      this.userOperador = this.authService.getUsuario1();
      this.userSupervisor = this.authService.getUsuario2();
  

      if(this.userOperador.id === id){
        this.authService.setUserLogueado(this.userOperador);
        this.authService.setUsuario2(this.userIndefinido);
        console.log(this.userOperador);
        localStorage.setItem('token', id);
        this.router.navigate(['./home']); 
      }
      else if(this.userSupervisor.id === id){
        this.authService.setUserLogueado(this.userSupervisor);
        this.authService.setUsuario1(this.userIndefinido);
        console.log(this.userSupervisor);
        localStorage.setItem('token', id);
        this.router.navigate(['./home']); 
      }

    //Si todo sale bien me redirige a:
    // this.router.navigate(['./storagePackages']);

  }

// get auth(){
// return this.authService.getUsuario1();
// }


// logout(){
// this.router.navigate(['./auth']);
// }

}
