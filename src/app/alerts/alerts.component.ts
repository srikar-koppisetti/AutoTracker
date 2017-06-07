import { Component, OnInit } from '@angular/core';
import { AlertsService } from './alerts.service';


import 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-alerts',
  templateUrl: './alerts.component.html',
  styleUrls: ['./alerts.component.css']
})
export class AlertsComponent implements OnInit {

  alerts = [];

  vinAlerts = [];

  vin = '';

  display = false;

  constructor(private alertsService: AlertsService) { }

  onGetPeriod(){
    this.alertsService.getHighAlerts()
      .subscribe(
        (data: any[]) => this.alerts = data,
        (error) => console.log(error)
      );
  }


  onGetVinAlert(){
    this.alertsService.getVinAlerts(this.vin)
      .subscribe(
        (data: any[]) => this.vinAlerts = data,
        (error) => console.log(error)
      );
    if(this.vinAlerts != null){
      this.display = true;
    }

  }


  onClickTop(){
    document.body.scrollTop = document.documentElement.scrollTop = 0;
  }


  ngOnInit() {
  }

}
