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

  constructor(private alertsService: AlertsService) { }

  onGetPeriod(){
    this.alertsService.getServers()
      .subscribe(
        (data: any[]) => this.alerts = data,
        (error) => console.log(error)
      );
  }



  ngOnInit() {
  }

}
