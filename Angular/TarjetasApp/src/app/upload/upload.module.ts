import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExcelFileUploadComponent } from './components/excel-file-upload/excel-file-upload.component';
import { AlmacenarComponent } from '../package/pages/almacenar/almacenar.component';
import { DistribuirComponent } from '../delivery/pages/distribuir/distribuir.component';
import { CrearComponent } from '../location/pages/crear/crear.component';




@NgModule({
  declarations: [
    ExcelFileUploadComponent
  ],
  imports: [
    CommonModule    
  ],
  exports: [
    ExcelFileUploadComponent
  ],
  providers:[AlmacenarComponent, DistribuirComponent, CrearComponent]
})
export class UploadModule { }
