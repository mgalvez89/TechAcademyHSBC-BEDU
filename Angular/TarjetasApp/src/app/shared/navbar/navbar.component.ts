import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Auth } from 'src/app/auth/interfaces/auth.interface';
import { AuthService } from 'src/app/auth/services/auth.service';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styles: [
  ]
})
export class NavbarComponent {

  title: string = 'App Tarjetas';
  id: string = '';
  user!: Auth;
  usuario1!: Auth;
  usuario2!: Auth;
  constructor( private router: Router,
               private authService: AuthService){}

    get auth(){  
    this.id = this.authService.getId();        
    return this.authService.getUserLogueado();
  }

  logout(){
    this.user = { id: "", email: "", usuario: "", rol:"" };
    this.usuario1 = { id: "1", email: "mperez01@correo.com", usuario: "mGarcia01", rol:"operador" };
    this.usuario2 = { id: "2", email: "gValencia02@correo.com", usuario: "gRobles02", rol:"supervisor" };
    this.authService.setUserLogueado(this.user);
    this.authService.setUsuario1(this.usuario1);
    this.authService.setUsuario2(this.usuario2);
    console.log("logoutLogueado: " , this.authService.getUserLogueado().id);
    console.log("logout1: " , this.authService.getUsuario1().id);
    console.log("logout2: " , this.authService.getUsuario2().id);
    this.router.navigate(['./auth']);
  }

}
