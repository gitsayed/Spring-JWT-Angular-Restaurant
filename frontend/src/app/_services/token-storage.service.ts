import { Injectable } from '@angular/core';

const TOKEN_KEY:string = 'auth-token';
const USER_KEY :string = 'auth-user';
const IS_LOGGED_IN :string = "isLoggedIn";

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  
  constructor() { }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string|null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user:any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    let authUser = sessionStorage.getItem(USER_KEY);
    return authUser? JSON.parse(authUser):null;
  }
  
  public setIsLoggedIn(value: boolean): any {
    return sessionStorage.setItem( IS_LOGGED_IN, JSON.stringify(value) );
 
  }
  public getIsLoggedIn(): any {
    let isLoggedIn = sessionStorage.getItem( IS_LOGGED_IN);
    return isLoggedIn?JSON.parse(isLoggedIn):false;
 
  }
}
