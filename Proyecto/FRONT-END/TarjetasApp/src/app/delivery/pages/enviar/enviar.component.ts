import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { uPackage } from 'src/app/package/interfaces/package-interface';
import Swal from 'sweetalert2';
import { DeliveryService } from '../../services/delivery.service';

@Component({
  selector: 'app-enviar',
  templateUrl: './enviar.component.html',
  styles: [
  ]
})
export class EnviarComponent {

  errorMessage = "";
  hayError: boolean = false;
  packagesList: uPackage[] = [];  

  constructor( private activateRoute: ActivatedRoute,               
               private deliveryService: DeliveryService,
               private router: Router ){
  }

  ngOnInit(): void {
    this.activateRoute.params
    .pipe(
      switchMap( ({ idRequest }) => this.deliveryService.getListPackagesBranchByIdRequest( idRequest ) )
    )
    .subscribe( data => this.packagesList = data );
    
  }

  enviar(){
    
    this.activateRoute.params
    .pipe(
      switchMap( ({ idRequest }) => this.deliveryService.sendPackages( idRequest ) )
    )
    .subscribe( respOk => {      
      Swal.fire({            
        icon: 'success',
        title: '¡Los paquetes fueron enviados correctamente!',
        showConfirmButton: false,
        timer: 1500
      })                  
      this.router.navigate(['./deliveryPackages']);       
      },
     respError =>{
      Swal.fire({
        icon: 'info',
        title: 'Info',
        html:'<b>' + respError + '</b>',
        confirmButtonText:'Aceptar'
      }) 
      this.errorMessage = respError;
      this.hayError = true;
     });
  }

  print() {    
    window.print();
  }

  
}
