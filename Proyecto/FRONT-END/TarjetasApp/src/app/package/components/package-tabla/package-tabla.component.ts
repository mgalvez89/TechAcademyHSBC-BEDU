import { Component, Input } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Solicitud } from '../../interfaces/package-interface';


@Component({
  selector: 'app-package-tabla',
  templateUrl: './package-tabla.component.html',
  styles: [
  ]
})
export class PackageTablaComponent {
  @Input() requests: Solicitud[] = [];

  constructor( private authService: AuthService){}
  
  get usuario(){  
    return this.authService.usuario;
  }
}
