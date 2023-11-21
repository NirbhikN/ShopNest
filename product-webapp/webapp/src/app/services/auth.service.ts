import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, map, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {JwtPayload} from "../model/JwtPayload.model";
import jwtDecode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private Url = 'http://localhost:8081/user/register';
  private apiUrl = 'http://localhost:8082/api/auth'; // Replace with your backend API URL
  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient) {
    this.checkToken();
  }


  registerUser(userData: any) {
    return this.http.post(this.Url, userData);
  }
  login(email: string, password: string): Observable<boolean> {
    // Send a POST request to the server with user credentials
    return this.http.post<any>(`${this.apiUrl}/login`, { email, password })
        .pipe(
            map(response => {
              // If the server returns a JWT token, store it in local storage
              if (response && response.token) {
                localStorage.setItem('token', response.token);
                this.loggedIn.next(true);
                console.log(response.token)// Update the loggedIn status
                return true;
              } else {
                return false;
              }
            }), catchError((error) => {
            // Handle the error (e.g., show a notification)
            console.error('Login failed:', error);
            throw error; // Rethrow the error to propagate it to the component
          })
        );
  }



  isAuthenticated(): boolean {
    const token = localStorage.getItem('token'); // You can change this to your storage method (e.g., cookies)
    return !!token; // Returns true if token exists, false otherwise
  }

  private checkToken(): void {
    // Check if a token exists in local storage when the service is created
    const token = localStorage.getItem('token');
    if (token) {
      this.loggedIn.next(true);
    }
  }

  getJwtToken() : boolean {
    const token = localStorage.getItem('token');
    if (token) {
      this.loggedIn.next(true);
      return true;
    }
    return false;
  }

  getJwtData():JwtPayload|null{
    const token = localStorage.getItem('token');

    if (token) {
      try {
        return jwtDecode(token);
      } catch (error) {
        // Handle any potential errors, such as invalid tokens
        console.error('Error decoding JWT:', error);
        return null;
      }
    } else {
      // Handle the case when the token is not found in localStorage
      console.error('JWT token not found in localStorage');
      return null;
    }
  }

}
