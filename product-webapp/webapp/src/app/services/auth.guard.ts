import { inject } from '@angular/core';
import {CanActivateFn, Router, NavigationExtras, UrlTree} from '@angular/router';
import {MyRestService} from "./my-rest.service";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";

export const authGuard: CanActivateFn = (route, state):Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree => {
  const loginService = inject(MyRestService);
  const router = inject(Router);
  const authService = inject(AuthService);
  const token:boolean = authService.getJwtToken();

  if (token) {
    // Token is present, allow access
    return true;
  } else {
    // Token is not present, redirect to the login page
    router.navigate(['/login']);
    return false;
  }
};
