import { Component } from '@angular/core';
import { Solicitud } from 'src/app/package/interfaces/package-interface';
import { PackageService } from 'src/app/package/services/package.service';
import { AlmacenarComponent } from '../../../package/pages/almacenar/almacenar.component';

@Component({
  selector: 'app-distribuir',
  templateUrl: './distribuir.component.html',
  styles: [
  ]
})
export class DistribuirComponent {
  
  solicitudes : Solicitud[] = [];
  hayError: boolean = false;
  
  constructor( private packageService: PackageService ){

  }  
  
  ngOnInit(): void {    
    this.packageService.listRequests( 'DELIVERY' )
    .subscribe( (requestsList) =>{              
      this.solicitudes = requestsList;               
    }, (error) =>{
      this.hayError = true;
      this.solicitudes = [];
    })
  }
}
