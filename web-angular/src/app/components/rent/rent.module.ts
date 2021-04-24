import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RentComponent } from './rent.component';
import { RentGridComponent } from './rent-grid/rent-grid.component';
import { TableModule } from 'src/app/table/table.module';



@NgModule({
  declarations: [ 
    RentComponent, 
    RentGridComponent
  ],
  imports: [
    CommonModule,
    TableModule
  ],
  exports: [ RentComponent ]
})
export class RentModule { }
