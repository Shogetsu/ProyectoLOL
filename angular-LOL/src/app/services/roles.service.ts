import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Rol } from '../interfaces/rol';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  private rolURL='http://localhost:8080/api/roles';

  constructor(private http:HttpClient) { }

  getRoles(): Observable<Rol[]>{
    //Se devuelve la llamada al servidor web
    return this.http.get<Rol[]>(this.rolURL);
  }

}
