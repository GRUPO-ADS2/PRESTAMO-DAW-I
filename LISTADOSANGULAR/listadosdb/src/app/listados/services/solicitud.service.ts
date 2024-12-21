import { Injectable } from '@angular/core';
import { Material } from '../models/Material';
import { catchError, map, Observable, of, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
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
    console.log('Request body:', body); // Para depuración
    return this.http.post(this.urlBase + '/solicitud', body, { responseType: 'text' })
      .pipe(
        map(response => response as string),
        catchError(this.handleError)
      );
  }
ListSolicitudes(estado: string): Observable < Solicitud[] > {
  return this.http.get<Solicitud[]>(this.urlBase + '/solicitudes/' + estado)
}

private handleError(error: HttpErrorResponse) {
  if (error.error instanceof ErrorEvent) {
    // Error del lado del cliente o de la red
    console.error('An error occurred:', error.error.message);
  } else {
    // El backend devolvió un código de respuesta no exitoso
    console.error(
      `Backend returned code ${error.status}, ` +
      `body was: ${error.error}`);
  }
  // Devuelve un observable con un mensaje de error para el usuario
  return throwError(
    'Something bad happened; please try again later.');
}

}
