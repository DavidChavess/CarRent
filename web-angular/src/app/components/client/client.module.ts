import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientComponent } from './client.component';
import { TableModule } from 'src/app/table/table.module';
import { GridComponent } from './grid/grid.component';


@NgModule({
  declarations: [ ClientComponent, GridComponent ],
  imports: [
    CommonModule,
    TableModule,
  ],
  exports: [
    ClientComponent
  ]
})
export class ClientModule { }
