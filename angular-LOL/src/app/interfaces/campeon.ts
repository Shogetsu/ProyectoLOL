import { Habilidad } from './habilidad';

export interface Campeon {
  idCampeon?: number;
  nombre: string;
  descripcion: string;
  dificultad: number;
  imagen: string;
  posicion: string;
  roles: {
    idRol: number;
    nombreRol: string;
  };
  habilidadeses:Habilidad[]


 /* habilidadeses?: [
    {
      idHabilidad: number;
      nombreHabilidad: string;
      descripcionHabilidad: string;
      letraHabilidad: string;
    },
    {
      idHabilidad: number;
      nombreHabilidad: string;
      descripcionHabilidad: string;
      letraHabilidad: string;
    },
    {
      idHabilidad: number;
      nombreHabilidad: string;
      descripcionHabilidad: string;
      letraHabilidad: string;
    },
    {
      idHabilidad: number;
      nombreHabilidad: string;
      descripcionHabilidad: string;
      letraHabilidad: string;
    },
    {
      idHabilidad: number;
      nombreHabilidad: string;
      descripcionHabilidad: string;
      letraHabilidad: string;
    }
  ];*/


}
