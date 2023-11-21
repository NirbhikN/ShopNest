import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {MatDialog} from "@angular/material/dialog";
import {ForgetpasswordComponent} from "../forgetpassword/forgetpassword.component";
import jwtDecode from "jwt-decode";
import {JwtPayload} from "../model/JwtPayload.model";
import {MyRestService} from "../services/my-rest.service";
import {Observable} from "rxjs";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent{
  credentials = { email: '', password: '' };
  check : string;
  constructor(private authService: AuthService,
              private router : Router,
              private toaster : ToastrService,
              private dialog: MatDialog,
              private rs: MyRestService
) {}

  openForgotPasswordDialog() {
    const dialogRef = this.dialog.open(ForgetpasswordComponent);
  }
  login(): void {
    let login: Observable<any> = this.authService.login(this.credentials.email, this.credentials.password);
    login.subscribe({
      next: (value) => {
        console.log(value)
      },
      error: (e) => {
        console.error(this.toaster.success("Invalid Username or Password"));
      },
      complete: () => {
        const token = localStorage.getItem("token");
        const decode :JwtPayload = jwtDecode(token);
        this.toaster.success("Hi, "+ decode.name);
        this.router.navigate(['']);
      },
    });
  }
}
