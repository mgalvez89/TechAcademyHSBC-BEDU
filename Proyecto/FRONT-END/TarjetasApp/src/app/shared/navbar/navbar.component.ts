import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from 'src/app/auth/services/auth.service';



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styles: [
  ]
})
export class NavbarComponent {

  constructor( private router: Router, 
               private authService: AuthService ){

  }


  get usuario(){  
    return this.authService.usuario;
  }

  logout(){ 
    this.router.navigate(['./auth']);
    this.authService.logout();
  } 
    

}
