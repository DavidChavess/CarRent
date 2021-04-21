import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './header.component';
import { routing } from '../app.routing';



@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    routing
  ],
  exports: [ HeaderComponent ]
})
export class HeaderModule { }
