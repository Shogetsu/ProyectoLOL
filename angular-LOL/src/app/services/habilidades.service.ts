import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Habilidad } from '../interfaces/habilidad';
import { HabilidadesResponse, HabilidadResponse } from '../interfaces/responses';

@Injectable({
  providedIn: 'root'
})
export class HabilidadesService {

  private habilidadURL='http://localhost:8080/api/habilidades';

  constructor(private http:HttpClient) { }

  getHabilidadCampeon(id: number): Observable<Habilidad[]> {
    return this.http.get<Habilidad[]>(this.habilidadURL+'/campeon/'+id);
  }

  insertHabilidad(habilidad:Habilidad):Observable<Habilidad>{
    return this.http.post<HabilidadResponse>(this.habilidadURL, habilidad).pipe(
      map(resp => resp.habilidad)
    );
  }
}
