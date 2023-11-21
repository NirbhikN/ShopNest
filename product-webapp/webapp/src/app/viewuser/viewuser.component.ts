import {Component, OnInit} from '@angular/core';
import jwtDecode from "jwt-decode";
import {Profile} from "../model/Profile.model";
import {HttpClient} from "@angular/common/http";



@Component({
  selector: 'app-viewuser',
  templateUrl: './viewuser.component.html',
  styleUrls: ['./viewuser.component.css']
})
export class ViewUserComponent implements OnInit{

  token = localStorage.getItem('token');
  decodedToken = jwtDecode(this.token);
  userData: Profile;
  // @ts-ignore
  userEmail = this.decodedToken.sub;
  private apiUrl :string = 'http://localhost:8081';
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.getUserDetails(this.userEmail);

  }

getUserDetails(email:string){
    const url = `${this.apiUrl}/user/email/${email}`
    this.http.get<Profile>(url).subscribe(
    (data => {
      this.userData = data;
    }));}
}
