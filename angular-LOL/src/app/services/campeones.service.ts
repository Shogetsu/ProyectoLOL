import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Campeon } from '../interfaces/campeon';
import { CampeonResponse } from '../interfaces/responses';

@Injectable({
  providedIn: 'root'
})
export class CampeonesService {

  private campeonURL='http://localhost:8080/api/campeones';

  constructor(private http:HttpClient) { }

  getCampeones(): Observable<Campeon[]>{
    //Se devuelve la llamada al servidor web
    return this.http.get<Campeon[]>(this.campeonURL);
  }

  insertCampeon(campeon:Campeon):Observable<Campeon>{
    return this.http.post<CampeonResponse>(this.campeonURL, campeon).pipe(
      map(resp => resp.campeon)
    );
  }

 /* getCampeon(id: number): Observable<Campeon> {
    return this.http.get<CampeonResponse>(this.campeonURL+'/'+id).pipe(
      map(resp => resp.campeon)
    );
  }*/

  getCampeon(id: number): Observable<Campeon> {
    return this.http.get<Campeon>(this.campeonURL+'/'+id);
  }

}
