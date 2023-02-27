import { Component, OnInit } from '@angular/core';
import { Solicitud } from '../../interfaces/package-interface';
import { PackageService } from '../../services/package.service';

@Component({
  selector: 'app-almacenar',
  templateUrl: './almacenar.component.html',
  styles: [
  ]
})
export class AlmacenarComponent implements OnInit{

  
  solicitudes : Solicitud[] = [];
  hayError: boolean = false;
  
  constructor( private packageService: PackageService){

  }  
  
  ngOnInit(): void {       
    this.packageService.listRequests( 'STORAGE' )
    .subscribe( (requestsList) =>{              
      this.solicitudes = requestsList;               
    }, (error) =>{
      this.hayError = true;
      this.solicitudes = [];
    })
  }


}
