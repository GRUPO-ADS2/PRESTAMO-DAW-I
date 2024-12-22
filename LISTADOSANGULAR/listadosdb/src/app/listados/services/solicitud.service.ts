import { Injectable } from '@angular/core';
import { Material } from '../models/Material';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Solicitud } from '../models/Solicitud';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {

  materiales: Material[] = []
  private urlBase: string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }


  ListMateriales(page: number, cat: string, nom: string): Observable<any> {
    let param = nom == "" ? "" : '?nom=' + nom
    return this.http.get<any>(`${this.urlBase}/materiales/${page}/${cat}${param}`);
  }
  create({ username, materialCod, cantidad }: any): Observable<string> {
    const body = { username, materialCod, cantidad };
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<string>(this.urlBase + '/solicitud', body, { headers, responseType: 'text' as 'json' })    
  }
ListSolicitudes(estado: string): Observable < Solicitud[] > {
  return this.http.get<Solicitud[]>(this.urlBase + '/solicitudes/' + estado)
}


}
