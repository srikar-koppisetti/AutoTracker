import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Injectable()
export class AutoDetailService {

  constructor(private http: Http){}

  getServers(){
    return this.http.get('http://localhost:8080/api/api/vehicles')
    .map(
      (response: Response) => {
        const data = response.json();
        return data;
      }
    );
  }

}
