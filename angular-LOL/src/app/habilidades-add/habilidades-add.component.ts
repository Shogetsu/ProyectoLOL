import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Campeon } from '../interfaces/campeon';
import { Habilidad } from '../interfaces/habilidad';
import { CampeonesService } from '../services/campeones.service';
import { HabilidadesService } from '../services/habilidades.service';

@Component({
  selector: 'habilidades-add',
  templateUrl: './habilidades-add.component.html',
  styleUrls: ['./habilidades-add.component.css']
})
export class HabilidadesAddComponent implements OnInit {

  campeones!:Campeon[];
  newHabilidad!:Habilidad;

  constructor(private campeonesService:CampeonesService, private habilidadesService:HabilidadesService, private router: Router) { }

  ngOnInit(): void {
    this.resetForm();
    this.campeonesService.getCampeones().subscribe(
      camp=>{
        this.campeones = camp;
      },
      error => console.error(error), // Error function (optional)
      () => console.log('Petición completada') // Finally function (optional)
    );
  }

  addHabilidad():void{
    this.newHabilidad.letraHabilidad = ((document.getElementById("letra") as HTMLInputElement).value);
    this.newHabilidad.campeones.idCampeon = +((document.getElementById("campeon") as HTMLInputElement).value);
    this.habilidadesService.insertHabilidad(this.newHabilidad).subscribe(
      camp => {
        //this.campeonAdded.emit(camp);
        this.resetForm();
        this.router.navigate(['/campeones']);
      },
      error => console.error(error)
    );
  }

  resetForm():void{
    this.newHabilidad={
      idHabilidad: 0,
      nombreHabilidad: '',
      descripcionHabilidad: '',
      letraHabilidad: '',
      campeones: {
        idCampeon: 0
      }
    };
  }
}
