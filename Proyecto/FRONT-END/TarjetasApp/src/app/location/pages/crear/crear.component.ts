import { Component } from '@angular/core';
import { aLocation } from 'src/app/package/interfaces/package-interface';
import { LocationService } from '../../services/location.service';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styles: [
  ]
})
export class CrearComponent {

  ubicaciones : aLocation[] = [];
  hayError: boolean = false;
  
  constructor( private locationService: LocationService){

  }  
  
  ngOnInit(): void {       
    this.locationService.listLocation()
    .subscribe( (listLocation) =>{              
      this.ubicaciones = listLocation;                   
    }, (error) =>{
      this.hayError = true;
      this.ubicaciones = [];
    })
  }

}
