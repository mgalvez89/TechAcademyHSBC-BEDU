import { Component, Input, OnInit } from '@angular/core';
import { ExcelFileUploadService } from '../../services/excel-file-upload.service';
import { AlmacenarComponent } from '../../../package/pages/almacenar/almacenar.component';
import { DistribuirComponent } from '../../../delivery/pages/distribuir/distribuir.component';
import { CrearComponent } from 'src/app/location/pages/crear/crear.component';
import { Subject } from 'rxjs';
import { indicate } from './operators';
import Swal from 'sweetalert2';
import { AuthService } from 'src/app/auth/services/auth.service';


@Component({
  selector: 'app-excel-file-upload',
  templateUrl: './excel-file-upload.component.html',
  styles: [
  ]
})
export class ExcelFileUploadComponent{

  // file!: File;
  // errorMessage = "";
  hayError: boolean = false;
  @Input() tipoUrl: string = '';
  file: File | null = null

  loading$ = new Subject<boolean>();
  
   
  constructor(private excelFileUploadService: ExcelFileUploadService,
              private almacenar: AlmacenarComponent,
              private distribuir: DistribuirComponent,
              private location: CrearComponent,
              private authService: AuthService)
  {
    
  }  


  onFilechange(files: FileList | null): void {
    if (files) {
      this.file = files.item(0)
    }
  }

  //  onFilechange( event: any ){
  //   console.log( event.target.files[0]);
  //   this.file = event.target.files[0];
  // }

  upload(){    
    if(this.file){
      this.excelFileUploadService.uploadfile( this.file, this.tipoUrl, this.authService.usuario.userName! )
      .pipe(indicate(this.loading$))
      .subscribe( 
        respOk => {
          Swal.fire({            
            icon: 'success',
            title: '¡Los datos fueron cargados correctamente!',
            showConfirmButton: false,
            timer: 1500
          })
          
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
        },
        respError => {          
          Swal.fire( 'Info', respError, 'info' );  

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
                 
          this.hayError = true;
        })
    } else {
      Swal.fire( 'Error', '<strong>Por favor, primero seleccione un archivo Excel</strong>', 'error' );  
    }
  }


  get usuario(){      
    return this.authService.usuario;
  }
  
}

 

  




