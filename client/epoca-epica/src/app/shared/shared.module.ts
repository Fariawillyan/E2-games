import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FooterComponent } from './components/footer/footer.component';
import { AppMaterialModule } from './app-material/app-material/app-material.module';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';



@NgModule({
  declarations: [
    FooterComponent,

  ],
  imports: [
    CommonModule,
    AppMaterialModule
  ],
  exports: [
    FooterComponent,
    MatIconModule,
    MatToolbarModule

  ]
})
export class SharedModule { }
