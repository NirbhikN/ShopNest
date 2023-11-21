import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {NavigationEnd, Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent{
  title = 'webapp';
  isLoginOrRegisterPage: boolean = false;
  searchProduct :string = ''

  constructor(private router: Router) {
    // Subscribe to the router's navigation end event
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        // Check if the current route is the login or register page
        this.isLoginOrRegisterPage =
            event.url === '/login' || event.url === '/register';
      }
    });
  }

  onSearchTextEntered($event: string) {
  }
}



