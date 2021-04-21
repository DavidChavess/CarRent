import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentComponent } from './rent.component';
import { HeaderModule } from 'src/app/header/header.module';



@NgModule({
  declarations: [ RentComponent],
  imports: [
    CommonModule,
  ],
  exports: [ RentComponent ]
})
export class RentModule { }
