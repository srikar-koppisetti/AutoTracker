import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AutoDetailsComponent } from './auto-details/auto-details.component';
import { AutoDetailService } from './auto-details/auto-details.service';
import { AlertsComponent } from './alerts/alerts.component';
import { AlertsService } from './alerts/alerts.service';




const appRoutes: Routes = [
  { path: 'vehicles', component: AutoDetailsComponent },
  { path: 'alerts', component: AlertsComponent },

];


@NgModule({
  declarations: [
    AppComponent,
    AutoDetailsComponent,
    AlertsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [AutoDetailService, AlertsService],
  bootstrap: [AppComponent]
})
export class AppModule { }
