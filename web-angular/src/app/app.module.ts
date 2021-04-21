import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { routing } from './app.routing';
import { VehicleModule } from './components/vehicle/vehicle.module';
import { RentModule } from './components/rent/rent.module';
import { ClientModule } from './components/client/client.module';
import { HomeModule } from './components/home/home.module';
import { HeaderModule } from './header/header.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HeaderModule,
    VehicleModule,
    RentModule,
    ClientModule,
    HomeModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
