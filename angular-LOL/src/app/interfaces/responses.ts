import { Campeon } from "./campeon";
import { Habilidad } from "./habilidad";
import { Rol } from "./rol";

export interface CampeonesResponse {
  campeones: Campeon[];
}

export interface CampeonResponse {
  campeon: Campeon;
}

export interface HabilidadesResponse {
  habilidades: Habilidad[];
}

export interface HabilidadResponse {
  habilidad: Habilidad;
}
