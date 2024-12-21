import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Prestamo } from '../models/Prestamo';

@Injectable({
  providedIn: 'root'
})
export class PrestamoService {
  [x: string]: any;

  constructor(private http: HttpClient) { }
  private urlBase:string = 'http://localhost:8081';

  RegistrarPrestamo(PressDTO:any): Observable<Prestamo>{
    return this.http.post<Prestamo>(this.urlBase+"/prestamo",PressDTO)
  }

  ListPrestamos(): Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlBase+"/prestamos")
  }

  GetPrestamoById(idPrestamo: number): Observable<Prestamo>{
    return this.http.get<Prestamo>(this.urlBase+"/prestamo/"+idPrestamo)
  }

  registrarDevolucion(codPrestamo: number): Observable<Prestamo>{
    return this.http.put<Prestamo>(this.urlBase+"/devolucion/"+codPrestamo, null)
  }

  actualizarPrestamo(idprestamo: number): Observable<Prestamo>{
    return this.http.put<Prestamo>(this.urlBase+"/prestamo/",idprestamo)
  }

  findbyEstado (estado: string): Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlBase+"/prestamos/"+estado)
  }

}
