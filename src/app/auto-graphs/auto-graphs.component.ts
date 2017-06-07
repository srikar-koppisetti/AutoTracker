import { Component, OnInit } from '@angular/core';
import { AutoGraphsService } from './auto-graphs.service';

import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Component({
  selector: 'app-auto-graphs',
  templateUrl: './auto-graphs.component.html',
  styleUrls: ['./auto-graphs.component.css']
})
export class AutoGraphsComponent implements OnInit {

  vin = '';

  time = 0;

  readings = [];

  signal = '';

  constructor(private autoGraphsService : AutoGraphsService) { }

  onGetReadings(signal: string){
    this.autoGraphsService.getVehicleReadings(this.vin, this.time)
    .subscribe(
        (data: any[]) => {this.readings=data;this.generateGraph(signal);},
        (error) => console.log(error),
      );

  }


  public lineChartData: Array<any> = [
    {data: [], label: ''},
  ];
  public lineChartLabels: Array<any> = [];

  public lineChartOptions: any = {
    responsive: true
  };
  public lineChartColors = [
    { // dark grey
      backgroundColor: 'rgba(151, 11, 146,0.2)',
      borderColor: 'rgba(151, 11, 146,1)',
      pointBackgroundColor: 'rgba(151, 11, 146,1)',
      pointBorderColor: '#fff',
      pointHoverBackgroundColor: '#fff',
      pointHoverBorderColor: 'rgba(151, 11, 146,1)'
    }
  ];
  public lineChartLegend = true;
  public lineChartType = 'line';



  generateGraph(signal: string){


    const _lineChartData: Array<any> = new Array(this.lineChartData.length);
    for (let i = 0; i < this.lineChartData.length; i++) {
      _lineChartData[i] = {data: new Array(this.readings.length), label: signal};
      for (let j = 0; j < this.readings.length; j++) {
        if ( signal === 'speed' ) {
          _lineChartData[i].data[j] = this.readings[j].speed;
        } else if (signal === 'engineHp') {
          _lineChartData[i].data[j] = this.readings[j].engineHp;
        } else if (signal === 'engineRpm') {
          _lineChartData[i].data[j] = this.readings[j].engineRpm;
        } else if (signal === 'fuelVolume') {
          _lineChartData[i].data[j] = this.readings[j].fuelVolume;
        }
      }
    }
    this.lineChartData = _lineChartData;

    for (let k = 0; k < this.readings.length; k++) {
      this.lineChartLabels.push(k);
    }

    console.log(this.lineChartLabels);

    this.lineChartLabels = [];
    this.lineChartLabels = [];

    this.time = null;
    this.signal = null;

  }

  ngOnInit() {
  }

}
