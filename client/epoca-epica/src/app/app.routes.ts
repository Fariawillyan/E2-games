import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home/home.component';
import { LandingPageComponent } from './landing-page/landing-page.component';

export const routes: Routes = [

  { path: '', component: HomeComponent },
  { path: 'landing-page', component: LandingPageComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
