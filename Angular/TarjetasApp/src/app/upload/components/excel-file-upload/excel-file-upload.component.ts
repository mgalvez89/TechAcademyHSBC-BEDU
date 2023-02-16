import { Component, Input, OnInit } from '@angular/core';
import { ExcelFileUploadService } from '../../services/excel-file-upload.service';
import { AlmacenarComponent } from '../../../package/pages/almacenar/almacenar.component';
import { DistribuirComponent } from '../../../delivery/pages/distribuir/distribuir.component';
import { CrearComponent } from 'src/app/location/pages/crear/crear.component';


@Component({
  selector: 'app-excel-file-upload',
  templateUrl: './excel-file-upload.component.html',
  styles: [
  ]
})
export class ExcelFileUploadComponent{

  file!: File;
  errorMessage = "";
  hayError: boolean = false;
  @Input() tipoUrl: string = '';
  
   
  constructor(private excelFileUploadService: ExcelFileUploadService,
              private almacenar: AlmacenarComponent,
              private distribuir: DistribuirComponent,
              private location: CrearComponent)
  {
    
  }  

  onFilechange( event: any ){
    console.log( event.target.files[0]);
    this.file = event.target.files[0];
  }

  upload(){    
    if(this.file){
      this.excelFileUploadService.uploadfile( this.file, this.tipoUrl )
      .subscribe( 
        respOk => {
          if(this.tipoUrl === 'packages')
          {
            this.almacenar.ngOnInit();
          } else if( this.tipoUrl === 'deliveries' )
          {
            this.distribuir.ngOnInit();
          } else if(this.tipoUrl === 'locations')
          {
            this.location.ngOnInit();
          }    
          console.log(respOk);           
        },
        respError => {
          this.errorMessage = respError;
          this.hayError = true;
        })
    } else {
      alert( "Please select a file first" );      
    }
  }

}
