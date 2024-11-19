import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prestamo } from '../models/Prestamo';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {

  constructor(private http: HttpClient) { }
  private urlBase:string = 'http://localhost:8080';

  RegistrarPrestamo(PressDTO:any): Observable<Prestamo>{
    return this.http.post<Prestamo>(this.urlBase+"/prestamo",PressDTO)
  }
}
