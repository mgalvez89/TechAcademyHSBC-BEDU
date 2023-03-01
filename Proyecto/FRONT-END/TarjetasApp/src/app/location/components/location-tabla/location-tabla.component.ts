import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { AuthService } from 'src/app/auth/services/auth.service';
import { aLocation } from '../../../package/interfaces/package-interface';
import { LocationService } from '../../services/location.service';
import Swal from 'sweetalert2';
import { CrearComponent } from '../../pages/crear/crear.component';

@Component({
  selector: 'app-location-tabla',
  templateUrl: './location-tabla.component.html',
  styles: [
  ]
})
export class LocationTablaComponent{    
    @Input() locations: aLocation[] = [];
    
    constructor( private authService: AuthService,
                 private listLocations: CrearComponent,
                 private locationService: LocationService ){}

    get usuario(){  
      return this.authService.usuario;
    }

    borrar(id:number, ubicacion:string){
      
        this.locationService.deleteLocation(id)
        .subscribe( respOk => 
        {      
          Swal.fire({            
            icon: 'success',
            title: "¡La ubicación: '"+ ubicacion +"' se borró correctamente!",
            showConfirmButton: false,
            timer: 1500
          })  
        this.listLocations.ngOnInit();        
        },
        respError =>{          
          Swal.fire({
            icon: 'info',
            title: 'Info',
            html:'<strong>' + respError + '</strong>',
            confirmButtonText:'Aceptar'
          }) 
          
         }
        )
           
    }
  
}


