import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { DistribuirComponent } from './pages/distribuir/distribuir.component';
import { UploadModule } from '../upload/upload.module';
import { DeliveryTableComponent } from './components/delivery-table/delivery-table.component';
import { EnviarComponent } from './pages/enviar/enviar.component';



@NgModule({
  declarations: [
    DistribuirComponent,
    DeliveryTableComponent,
    EnviarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    UploadModule
  ],
  exports:[
    DeliveryTableComponent,
    DistribuirComponent
  ]
})
export class DeliveryModule { }
