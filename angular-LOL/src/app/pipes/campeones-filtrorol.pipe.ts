import { Pipe, PipeTransform } from '@angular/core';
import { Campeon } from '../interfaces/campeon';

@Pipe({
  name: 'campeonesFiltrorol'
})
export class CampeonesFiltrorolPipe implements PipeTransform {


  transform(campeones:Campeon[], filtro:string) {
    return campeones.filter(c=>c.roles.nombreRol!.toUpperCase().includes(filtro.toUpperCase()));
  }

}
