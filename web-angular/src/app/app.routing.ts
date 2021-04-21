import {RouterModule, Routes} from '@angular/router';
import { ModuleWithProviders } from '@angular/core';

import { ClientComponent } from './components/client/client.component'
import { VehicleComponent } from './components/vehicle/vehicle.component';
import { RentComponent } from './components/rent/rent.component';
import { HomeComponent } from './components/home/home.component';

const APP_ROUTES: Routes = [
    {
        path: "",
        component: HomeComponent,        
    },
    {
        path: "clients",
        component: ClientComponent,        
    },
    {
        path: "vehicles",
        component: VehicleComponent,        
    },
    {
        path: "rents",
        component: RentComponent,        
    }
]

 export const routing: ModuleWithProviders<any> = RouterModule.forRoot(APP_ROUTES);

