import { NgModule } from '@angular/core';
import { RouterModule, Routes, CanActivate, CanLoad } from '@angular/router';

import { DistribuirComponent } from './delivery/pages/distribuir/distribuir.component';
import { EnviarComponent } from './delivery/pages/enviar/enviar.component';
import { CrearComponent } from './location/pages/crear/crear.component';
import { AlmacenarComponent } from './package/pages/almacenar/almacenar.component';
import { VerPaquetesComponent } from './package/pages/ver-paquetes/ver-paquetes.component';
import { ErrorPageComponent } from './shared/error-page/error-page.component';
import { AuthGuard } from './auth/guards/auth.guard';
import { HomeComponent } from './package/pages/home/home.component';
import { LocationAccesoGuard } from './auth/guards/location-acceso.guard';
import { ValidarTokenGuard } from './auth/guards/validar-token.guard';
import { LocationTablaComponent } from './location/components/location-tabla/location-tabla.component';



const routes: Routes = [

    // En el siguiente ejemplo, proporcionar la estrategia pathMatch: 'full'  
    // asegura que el router aplique la redirección si y solo si navega hacia '/'.
    // {
    //     path: '',
    //     component: PorPaisComponent,
    //     pathMatch: 'full'
    // },
    {
        path: 'auth',
        loadChildren: () => import('./auth/auth.module').then( m => m.AuthModule)        
    },
    {
         path: 'home',
         component: HomeComponent,
         canActivate: [ ValidarTokenGuard ]
    },
    {
        path: 'createLocations',
        component: CrearComponent,
        canActivate: [ ValidarTokenGuard ]      

    },    
    {
        path: 'storagePackages',
        component: AlmacenarComponent,
        canActivate: [ ValidarTokenGuard ]     
    },
    {
        path: 'deliveryPackages',
        component: DistribuirComponent,
        canActivate: [ ValidarTokenGuard ]            
    },
    {
        path: 'storageRequest/:idRequest',
        component: VerPaquetesComponent,
        canActivate: [ ValidarTokenGuard ]    
    },
    {
        path: 'deliveryRequest/:idRequest',
        component: EnviarComponent,
        canActivate: [ ValidarTokenGuard ]    
    },
    {
        path: '404',
        component: ErrorPageComponent
    },
    {
        path: '**',
        // component: ErrorPageComponent
        redirectTo: 'auth'
    }
]


@NgModule({
    imports: [
        RouterModule.forRoot( routes )
    ],
    exports: [
        RouterModule
    ],
})
export class AppRoutingModule{}