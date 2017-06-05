import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AutoDetailsComponent } from './auto-details/auto-details.component';
import { AutoDetailService } from './auto-details/auto-details.service';

const appRoutes: Routes = [
  { path: 'vehicles', component: AutoDetailsComponent }
];


@NgModule({
  declarations: [
    AppComponent,
    AutoDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AutoDetailService],
  bootstrap: [AppComponent]
})
export class AppModule { }
