import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Package, uPackage } from '../../interfaces/package-interface';
import { PackageService } from '../../services/package.service';

@Component({
  selector: 'app-ver-paquetes',
  templateUrl: './ver-paquetes.component.html',
  styles: [
  ]
})
export class VerPaquetesComponent implements OnInit {
  
  packagesList: Package[] = [];

  constructor( private activateRoute: ActivatedRoute,
               private packageService: PackageService ){
  }

  ngOnInit(): void {
    this.activateRoute.params
    .pipe(
      switchMap( ({ idRequest }) => this.packageService.getListPackagesByIdRequest( idRequest ) )
    )
    .subscribe( data => this.packagesList = data );
  }

  print() {    
    window.print();
  }

}
