import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor() { }

  onClick(){
    document.body.scrollTop = document.documentElement.scrollTop = 0;
  }

  ngOnInit() {
  }

}
