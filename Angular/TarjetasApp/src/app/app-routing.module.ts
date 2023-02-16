import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DistribuirComponent } from './delivery/pages/distribuir/distribuir.component';
import { EnviarComponent } from './delivery/pages/enviar/enviar.component';
import { CrearComponent } from './location/pages/crear/crear.component';
import { AlmacenarComponent } from './package/pages/almacenar/almacenar.component';
import { VerPaquetesComponent } from './package/pages/ver-paquetes/ver-paquetes.component';
import { ErrorPageComponent } from './shared/error-page/error-page.component';
import { AuthGuard } from './auth/guards/auth.guard';
import { HomeComponent } from './package/pages/home/home.component';
import { LocationAccesoGuard } from './auth/guards/location-acceso.guard';



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
         component: HomeComponent
    },
    {
        path: 'createLocations',
        component: CrearComponent,
        canActivate: [LocationAccesoGuard]        

    },
    {
        path: 'storagePackages',
        component: AlmacenarComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'deliveryPackages',
        component: DistribuirComponent,
        canActivate: [AuthGuard]             
    },
    {
        path: 'storageRequest/:idRequest',
        component: VerPaquetesComponent,
        canActivate: [AuthGuard]   
    },
    {
        path: 'deliveryRequest/:idRequest',
        component: EnviarComponent,
        canActivate: [AuthGuard] 
    },
    {
        path: '404',
        component: ErrorPageComponent
    },
    {
        path: '**',
        // component: ErrorPageComponent
        redirectTo: '404'
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