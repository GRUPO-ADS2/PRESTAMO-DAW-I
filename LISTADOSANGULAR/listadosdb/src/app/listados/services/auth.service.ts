import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private urlBase:string = 'http://localhost:8081';
  constructor(private http:HttpClient) { }
  private _token: string | undefined;
  private _user: any ={
    isAuth: false,
    user: undefined,
    isAdmin : false
  }

  
  
  set token(token: string) {
    this._token = token;
    if (typeof window !== 'undefined' && sessionStorage) {
      sessionStorage.setItem('token', token);
    }
  }

  get token() {
    if (this._token) {
      return this._token;
    } else if (typeof window !== 'undefined' && sessionStorage.getItem('token')) {
      this._token = sessionStorage.getItem('token') || '';
      return this._token;
    }
    return this._token!;
  }

  getPayLoad(token: string) {
    if (token) {
      return JSON.parse(atob(token.split(".")[1]));
    }
    return null;
  }

  isAdmin(): boolean {
    return this.user?.isAdmin || false;
  }

  authenticated(): boolean {
    return this.user?.isAuth || false;
  }

  get user() {
    if (typeof window !== 'undefined' && sessionStorage) {
      const user = sessionStorage.getItem('user');
      return user ? JSON.parse(user) : this._user;
    }
    return this._user;
  }

  set user(user: any) {
    this._user = user;
    if (typeof window !== 'undefined' && sessionStorage) {
      sessionStorage.setItem('user', JSON.stringify(user));
    }
  }
  
  loginUser({username, contrasenia}: any): Observable<any>{
    console.log(username, contrasenia);
    return this.http.post<any>(this.urlBase+"/login", {username, contrasenia})
  }
  logout(){
    this._user = {
      isAuth: false,
      user: undefined,
      isAdmin: false
    }
    this._token = '';
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
  }
}
