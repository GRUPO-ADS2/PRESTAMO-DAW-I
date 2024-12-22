import { HttpClient,HttpHeaders } from '@angular/common/http';
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

  RegistrarPrestamo({
    solicitudId,
    fechaPrestamo
  }: any): Observable<string> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<string>(this.urlBase + "/prestamo", {
      solicitudId,
      fechaPrestamo
    }, { headers, responseType: 'text' as 'json' })
  }

  ListPrestamos(): Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlBase+"/prestamos")
  }

  GetPrestamoById(idPrestamo: number): Observable<Prestamo>{
    return this.http.get<Prestamo>(this.urlBase+"/prestamo/"+idPrestamo)
  }

  registrarDevolucion(codPrestamo: number): Observable<string>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<string>(this.urlBase+"/devolucion/"+codPrestamo, null, { headers, responseType: 'text' as 'json' });
  }

  actualizarPrestamo(idprestamo: number): Observable<Prestamo>{
    return this.http.put<Prestamo>(this.urlBase+"/prestamo/",idprestamo)
  }

  findbyEstado (estado: string): Observable<Prestamo[]>{
    return this.http.get<Prestamo[]>(this.urlBase+"/prestamos/"+estado)
  }

  renovar(idPrestamo: number): Observable<string> {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      return this.http.put<string>(this.urlBase+"/prestamo/"+idPrestamo, null, { headers, responseType: 'text' as 'json' });
    }

    devolver(idPrestamo: number): Observable<string> {
      const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
      return this.http.put<string>(this.urlBase+"/devolucion/"+idPrestamo,null, { headers, responseType: 'text' as 'json' });
    }
}
