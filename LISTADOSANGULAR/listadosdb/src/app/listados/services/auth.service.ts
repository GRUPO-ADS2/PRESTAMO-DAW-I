import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private urlBase:string = 'http://localhost:8081';
  private _token: string | undefined;
  private _user: any ={
    isAuth: false,
    user: undefined,
    isAdmin : false
  }
  constructor(private http:HttpClient) { }

  loginUser({username, contrasenia}: any): Observable<any>{
    return this.http.post<any>(this.urlBase+"/login", {username, contrasenia})
  }

  set user(user: any){
    this._user = user;
    sessionStorage.setItem('login', JSON.stringify(user));
  }
  get user(){
    if(this._user.isAuth){
      return this._user;
    }else if(sessionStorage.getItem('login')){
      this._user = JSON.parse(sessionStorage.getItem('login') || '{}');
      return this._user;
    }  
    return this._user;

  }
  set token(token: string){
    this._token = token;
    sessionStorage.setItem('token', token);
  }
  get token(){
    if(this._token){
      return this._token;
    }else if(sessionStorage.getItem('token') &&typeof window !== 'undefined'){
      this._token = sessionStorage.getItem('token') || '';
      return this._token;
    }
    return this._token!;
  }

  getPayLoad(token: string){
    if(token){
      return JSON.parse(atob(token.split(".")[1]));
    }
    return null;
  } 

  isAdmin(): boolean{
    return this.user.isAdmin;
  }
  authenticated(): boolean{
    return this.user.isAuth;
  }

  logout(){
    this._user = {
      isAuth: false,
      user: undefined,
      isAdmin: false
    }
    this._token = '';
    sessionStorage.removeItem('login');
    sessionStorage.removeItem('token');
  }
}
