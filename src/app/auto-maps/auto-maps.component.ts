import { Component, OnInit } from '@angular/core';
import {AutoMapService} from './auto-maps.service';

import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Component({
  selector: 'app-auto-maps',
  templateUrl: './auto-maps.component.html',
  styleUrls: ['./auto-maps.component.css']
})
export class AutoMapsComponent implements OnInit {

  vin = '';

  time = 0;

  readings = [];

  zoom: number = 1;

  lat: number = 38.8799700;
  lng: number = -77.1067700;



  constructor(private autoMapService: AutoMapService){}

  onGetReadings(){
    this.autoMapService.getVehicleLocation(this.vin, this.time)
      .subscribe(
        (data: any[]) => this.readings = data,
        (error) => console.log(error),
      );

  }

  ngOnInit() {
  }

}
