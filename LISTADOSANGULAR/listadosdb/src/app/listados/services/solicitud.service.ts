import { Injectable } from '@angular/core';
import { Material } from '../models/Material';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Solicitud } from '../models/Solicitud';

@Injectable({
  providedIn: 'root'
})
export class SolicitudService {

  materiales: Material[]= []
  private urlBase:string = 'http://localhost:8081';

  constructor(private http: HttpClient) { }


  ListMateriales (page:number) : Observable<any>{
    return this.http.get<any>(`${this.urlBase}/materiales/${page}`);
  }
  create(SoliDTO:any): Observable<any>{
    return this.http.post<any>(this.urlBase+'/solicitud',SoliDTO)
  } 
  ListSolicitudes(estado: string): Observable<Solicitud[]> {
    return this.http.get<Solicitud[]>(this.urlBase+'/solicitudes/'+estado)
  }

}
