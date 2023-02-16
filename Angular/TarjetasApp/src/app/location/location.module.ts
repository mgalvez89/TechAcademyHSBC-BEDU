import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearComponent } from './pages/crear/crear.component';
import { ListarComponent } from './pages/listar/listar.component';
import { UploadModule } from '../upload/upload.module';
import { LocationTablaComponent } from './components/location-tabla/location-tabla.component';



@NgModule({
  declarations: [
    CrearComponent,
    ListarComponent,
    LocationTablaComponent
  ],
  imports: [
    CommonModule,
    UploadModule
  ]
})
export class LocationModule { }
