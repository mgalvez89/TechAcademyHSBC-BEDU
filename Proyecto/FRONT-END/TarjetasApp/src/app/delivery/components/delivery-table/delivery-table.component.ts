import { Component, Input } from '@angular/core';
import { AuthService } from 'src/app/auth/services/auth.service';
import { Solicitud } from 'src/app/package/interfaces/package-interface';

@Component({
  selector: 'app-delivery-table',
  templateUrl: './delivery-table.component.html',
  styles: [
  ]
})
export class DeliveryTableComponent {
  @Input() requests: Solicitud[] = [];

  constructor( private authService: AuthService){}
  
  get usuario(){  
    return this.authService.usuario;
  }
}
