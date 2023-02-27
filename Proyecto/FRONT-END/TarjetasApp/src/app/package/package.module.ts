import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { ListarComponent } from './pages/listar/listar.component';
import { AlmacenarComponent } from './pages/almacenar/almacenar.component';
import { UploadModule } from '../upload/upload.module';
import { PackageTablaComponent } from './components/package-tabla/package-tabla.component';
import { VerPaquetesComponent } from './pages/ver-paquetes/ver-paquetes.component';
import { HomeComponent } from './pages/home/home.component';



@NgModule({
  declarations: [
    ListarComponent,
    AlmacenarComponent,
    PackageTablaComponent,
    VerPaquetesComponent,
    HomeComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    UploadModule
  ],
  exports: [
    ListarComponent,
    AlmacenarComponent,
    PackageTablaComponent 
  ]
})
export class PackageModule { }
