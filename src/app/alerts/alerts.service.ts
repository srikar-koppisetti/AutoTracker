import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Injectable()
export class AlertsService{

  constructor(private http: Http){}

  getHighAlerts(){
    return this.http.get('http://localhost:8080/api/api/highAlerts')
      .map(
        (response: Response) => {
          const data = response.json();
          return data;
        }
      );
  }

  getVinAlerts(vin : String){
    return this.http.get('http://localhost:8080/api/api/alerts/'+vin)
      .map(
        (response: Response) => {
          const data = response.json();
          return data;
        }
      );
  }
}
