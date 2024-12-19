import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private urlBase:string = 'http://localhost:8081';
  constructor(private http:HttpClient) { }

  loginUser({username, contrasenia}: any): Observable<any>{
    return this.http.post<any>(this.urlBase+"/login", {username, contrasenia})
  }
}
