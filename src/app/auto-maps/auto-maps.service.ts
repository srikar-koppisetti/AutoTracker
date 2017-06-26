import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';

@Injectable()
export class AutoMapService{
  constructor(private http: Http){}

  getVehicleLocation(vin: String, time: number){
    return this.http.get('http://localhost:8080/api/api/readings/' + vin + '/time/' + time)
      .map(
        (response: Response) => {
          const data = response.json();
          return data;
        }
      );
  }
}
