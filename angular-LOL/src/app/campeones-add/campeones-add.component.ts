import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { Campeon } from '../interfaces/campeon';
import { Rol } from '../interfaces/rol';
import { CampeonesService } from '../services/campeones.service';
import { RolesService } from '../services/roles.service';

@Component({
  selector: 'campeones-add',
  templateUrl: './campeones-add.component.html',
  styleUrls: ['./campeones-add.component.css']
})
export class CampeonesAddComponent implements OnInit {

  nombreImagen='';
  roles!:Rol[];
  newCampeon!:Campeon;
  @Output() campeonAdded=new EventEmitter<Campeon>();

  constructor(private campeonesService:CampeonesService, private rolesService:RolesService,  private router: Router) { }

  ngOnInit(): void {
    this.resetForm();
    this.rolesService.getRoles().subscribe(
      listaRol=>{
        this.roles = listaRol;
      },
      error => console.error(error), // Error function (optional)
      () => console.log('Petición completada') // Finally function (optional)
    );

  }

  resetForm():void{
    this.newCampeon={
      nombre:'',
      descripcion:'',
      dificultad:1,
      imagen:'',
      posicion:'',
      roles: {
        idRol: 1,
        nombreRol: ''
      }
    };
    this.nombreImagen='';
  }

  addCampeon():void{
    this.newCampeon.roles.idRol = +((document.getElementById("rol") as HTMLInputElement).value);
    this.newCampeon.dificultad = +((document.getElementById("dificultad") as HTMLInputElement).value);
    this.newCampeon.posicion = ((document.getElementById("posicion") as HTMLInputElement).value);
    this.campeonesService.insertCampeon(this.newCampeon).subscribe(
      camp => {
        this.campeonAdded.emit(camp);
        this.resetForm();
        this.router.navigate(['/campeones']);
      },
      error => console.error(error)
    );
  }

  changeImage(fileInput: HTMLInputElement): void {
    if (!fileInput.files || fileInput.files.length === 0) {
      return;
    }
    const reader: FileReader = new FileReader();
    reader.readAsDataURL(fileInput.files[0]);
    reader.addEventListener('loadend', (e) => {
      this.newCampeon.imagen = reader.result as string;
    });
  }

  onOptionsSelected(value:string){
    console.log("the selected value is " + value);
}

}
