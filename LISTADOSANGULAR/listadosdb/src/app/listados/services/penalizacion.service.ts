import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Penalizacion } from '../models/Penalizacion';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PenalizacionService {

  constructor(private http:HttpClient) { }
  private urlBase:string = 'http://localhost:8081';

  listarPenalizaciones() : Observable<Penalizacion[]>{
    return this.http.get<Penalizacion[]>(this.urlBase+ "/penalizaciones");
  }
  findPenalizacionById(idPenalizacion: number): Observable<Penalizacion>{
    return this.http.get<Penalizacion>(this.urlBase+ "/penalizacion/"+idPenalizacion);
  }

  registrarPenalizacion(penaDTO: any): Observable<any>{
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post(this.urlBase+ "/penalizacion", penaDTO, { headers, responseType: 'text' });
  }
  penalizar(prestamo: any, descripcion: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { prestamo, descripcion };
    return this.http.post(this.urlBase, body, { headers, responseType: 'text' });
  }
  actualizarPenalizacion(idPenalizacion: number): Observable<Penalizacion>{
    return this.http.put<Penalizacion>(this.urlBase+ "/penalizacion", idPenalizacion);
  }
  
}
