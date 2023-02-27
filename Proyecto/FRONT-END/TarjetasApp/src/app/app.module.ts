import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { UploadModule } from './upload/upload.module';
import { AppRoutingModule } from './app-routing.module';
import { PackageModule } from './package/package.module';
import { LocationModule } from './location/location.module';
import { AlmacenarComponent } from './package/pages/almacenar/almacenar.component';
import { LoginComponent } from './auth/pages/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    AppComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    UploadModule, 
    SharedModule,   
    LocationModule,
    ReactiveFormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
