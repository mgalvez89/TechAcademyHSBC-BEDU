import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { switchMap } from 'rxjs';
import { Package } from 'src/app/package/interfaces/package-interface';
import { PackageService } from 'src/app/package/services/package.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-enviar',
  templateUrl: './enviar.component.html',
  styles: [
  ]
})
export class EnviarComponent {

  errorMessage = "";
  hayError: boolean = false;
  packagesList: Package[] = [];  

  constructor( private activateRoute: ActivatedRoute,
               private packageService: PackageService,
               private router: Router ){
  }

  ngOnInit(): void {
    this.activateRoute.params
    .pipe(
      switchMap( ({ idRequest }) => this.packageService.getListPackagesByIdRequest( idRequest ) )
    )
    .subscribe( data => this.packagesList = data );
    
  }

  enviar(){
    
    this.activateRoute.params
    .pipe(
      switchMap( ({ idRequest }) => this.packageService.sendPackages( idRequest ) )
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
      // Swal.fire( 'Error', respError, 'error' );
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
