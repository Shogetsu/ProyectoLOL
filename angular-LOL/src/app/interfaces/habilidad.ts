export interface Habilidad {
  idHabilidad?: number;
  nombreHabilidad: string;
  descripcionHabilidad: string;
  letraHabilidad: string;
  campeones: {
    idCampeon: number;
  };
}
