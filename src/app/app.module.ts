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
import { AutoGraphsComponent } from './auto-graphs/auto-graphs.component';
import { AutoGraphsService } from './auto-graphs/auto-graphs.service';
import {ChartsModule} from 'ng2-charts';
import { AutoMapsComponent } from './auto-maps/auto-maps.component';
import {AutoMapService} from './auto-maps/auto-maps.service';
import { AgmCoreModule } from 'angular2-google-maps/core';
import { HomeComponent } from './home/home.component';





const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'vehicles', component: AutoDetailsComponent },
  { path: 'alerts', component: AlertsComponent },
  { path: 'graphs', component: AutoGraphsComponent },
  { path: 'maps', component: AutoMapsComponent },
];


@NgModule({
  declarations: [
    AppComponent,
    AutoDetailsComponent,
    AlertsComponent,
    AutoGraphsComponent,
    AutoMapsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RouterModule.forRoot(appRoutes),
    ChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyALyDAVaAUVzcsAsplI8cHkW1BHlW0BHS8'
    })
  ],
  providers: [AutoDetailService, AlertsService, AutoGraphsService, AutoMapService],
  bootstrap: [AppComponent]
})
export class AppModule { }
