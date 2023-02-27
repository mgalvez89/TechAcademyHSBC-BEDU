import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { PackageModule } from '../package/package.module';
import { AlmacenarComponent } from '../package/pages/almacenar/almacenar.component';
import { DeliveryModule } from '../delivery/delivery.module';
import { ErrorPageComponent } from './error-page/error-page.component';




@NgModule({
  declarations: [
    NavbarComponent,
    ErrorPageComponent
  ],
  exports:[
    NavbarComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    PackageModule,
    DeliveryModule
  ],
  providers:[]
})
export class SharedModule { }
