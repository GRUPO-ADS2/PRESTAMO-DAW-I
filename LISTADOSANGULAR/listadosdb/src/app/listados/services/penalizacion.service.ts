import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Penalizacion } from '../models/Penalizacion';
import { HttpClient } from '@angular/common/http';

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

  registrarPenalizacion(penalizacion: any): Observable<Penalizacion>{
    return this.http.post<Penalizacion>(this.urlBase+ "/penalizacion", penalizacion);
  }

  actualizarPenalizacion(idPenalizacion: number): Observable<Penalizacion>{
    return this.http.put<Penalizacion>(this.urlBase+ "/penalizacion", idPenalizacion);
  }
  
}
