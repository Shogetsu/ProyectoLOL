import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Campeon } from '../interfaces/campeon';
import { Rol } from '../interfaces/rol';
import { CampeonesService } from '../services/campeones.service';
import { RolesService } from '../services/roles.service';

@Component({
  selector: 'campeon-modify',
  templateUrl: './campeon-modify.component.html',
  styleUrls: ['./campeon-modify.component.css']
})
export class CampeonModifyComponent implements OnInit {

  campeon!:Campeon;
  modificarCampeon!:Campeon;
  nombreImagen='';
  roles!:Rol[];

  constructor(private router: Router,
    private campeonesService: CampeonesService,
    private route: ActivatedRoute,
    private rolesService:RolesService) { }

  ngOnInit(): void {
    this.resetForm();
    this.route.params.subscribe((params) => {
      this.campeonesService.getCampeon(params.id).subscribe(
        (camp) => {
          this.campeon = camp;
        },
        (error) => this.router.navigate(['/campeones'])
      );
    });

    this.rolesService.getRoles().subscribe(
      listaRol=>{
        this.roles = listaRol;
      },
      error => console.error(error), // Error function (optional)
      () => console.log('Petición completada') // Finally function (optional)
    );
  }

  resetForm():void{
    this.modificarCampeon={
      nombre:'',
      descripcion:'',
      dificultad:1,
      imagen:'',
      posicion:'',
      roles: {
        idRol: 1
      },
      /*habilidadeses: [
        {
          idHabilidad: 0,
          nombreHabilidad: '',
          descripcionHabilidad: '',
          letraHabilidad: '',
        },
        {
          idHabilidad: 0,
          nombreHabilidad: '',
          descripcionHabilidad: '',
          letraHabilidad: '',
        },
        {
          idHabilidad: 0,
          nombreHabilidad: '',
          descripcionHabilidad: '',
          letraHabilidad: '',
        },
        {
          idHabilidad: 0,
          nombreHabilidad: '',
          descripcionHabilidad: '',
          letraHabilidad: '',
        },
        {
          idHabilidad: 0,
          nombreHabilidad: '',
          descripcionHabilidad: '',
          letraHabilidad: '',
        },
      ]*/
    };
    this.nombreImagen='';
  }

  changeImage(fileInput: HTMLInputElement): void {
    if (!fileInput.files || fileInput.files.length === 0) {
      return;
    }
    const reader: FileReader = new FileReader();
    reader.readAsDataURL(fileInput.files[0]);
    reader.addEventListener('loadend', (e) => {
      this.modificarCampeon.imagen = reader.result as string;
    });
  }

  modifyCampeon():void{
    this.modificarCampeon.nombre = ((document.getElementById("nombre") as HTMLInputElement).value);
    this.modificarCampeon.descripcion = ((document.getElementById("descripcion") as HTMLTextAreaElement).value);
    this.modificarCampeon.dificultad = +((document.getElementById("dificultad") as HTMLInputElement).value);
    this.modificarCampeon.posicion = ((document.getElementById("posicion") as HTMLInputElement).value);
    this.modificarCampeon.roles.idRol = +((document.getElementById("rol") as HTMLInputElement).value);
    this.modificarCampeon.imagen=this.campeon.imagen;
    console.log(this.modificarCampeon);
    /*this.modificarCampeon.nombre = document.getElementById("nombre").;
    console.log(this.modificarCampeon);*/
    this.campeonesService.modifyCampeon(this.modificarCampeon, this.campeon.idCampeon!).subscribe(
      camp => {
        this.resetForm();
        this.router.navigate(['/campeones']);
      },
      error => console.error(error)
    );
  }

}
