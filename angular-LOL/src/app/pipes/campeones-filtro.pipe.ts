import { Pipe, PipeTransform } from '@angular/core';
import { Campeon } from '../interfaces/campeon';

@Pipe({
  name: 'campeonesFiltro'
})
export class CampeonesFiltroPipe implements PipeTransform {

  transform(campeones:Campeon[], filtro:string) {
    return campeones.filter(c=>c.nombre.toUpperCase().includes(filtro.toUpperCase()) );
  }

}
