import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClientComponent } from './client.component';
import { HeaderModule } from 'src/app/header/header.module';



@NgModule({
  declarations: [ ClientComponent ],
  imports: [
    CommonModule,
  ],
  exports: [
    ClientComponent
  ]
})
export class ClientModule { }
