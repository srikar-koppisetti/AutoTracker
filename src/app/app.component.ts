import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isIn = false;

  onToggle(){
    let bool = this.isIn;
    this.isIn = bool === false ? true : false;
  }

  onNavToggle(){
    document.body.scrollTop = document.documentElement.scrollTop = 0;
    this.isIn = false;
  }
}
