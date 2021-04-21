import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VehicleComponent } from './vehicle.component';
import { HeaderModule } from 'src/app/header/header.module';



@NgModule({
  declarations: [ VehicleComponent ],
  imports: [
    CommonModule,
  ],
  exports: [VehicleComponent ]
})
export class VehicleModule { }
