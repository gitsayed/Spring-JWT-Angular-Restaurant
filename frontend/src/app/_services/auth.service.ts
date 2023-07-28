import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { baseURL } from '../constants';

const AUTH_API = baseURL +'/api/v1/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {


customerRole=["ROLE_CUSTOMER"];
restaurantRole=["ROLE_RESTAURANT"];

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signin',
      {
        username,
        password,
      },
      httpOptions
    );
  }



  register(username: string, email: string, password: string, category: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'signup',
      {
        username,
        email,
        password,
        roles:category==='restaurant'?this.restaurantRole:this.customerRole
      },
      httpOptions
    );
  }
}
