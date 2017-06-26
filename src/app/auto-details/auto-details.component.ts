import { Component, OnInit } from '@angular/core';
import { AutoDetailService } from './auto-details.service';

import 'rxjs/Rx';
import 'rxjs/add/operator/map';


@Component({
  selector: 'app-auto-details',
  templateUrl: './auto-details.component.html',
  styleUrls: ['./auto-details.component.css']
})
export class AutoDetailsComponent implements OnInit {

  auto =  [];

  constructor(private autoDetailService: AutoDetailService) { }

  onClick(){
    this.autoDetailService.getServers()
    .subscribe(
      (data: any[]) => this.auto = data,
      (error) => console.log(error)
    );
  }


  ngOnInit() {
  }

}
