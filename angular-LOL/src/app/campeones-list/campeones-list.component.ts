import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { CampeonesAddComponent } from '../campeones-add/campeones-add.component';
import { Campeon } from '../interfaces/campeon';
import { CampeonesFiltrorolPipe } from '../pipes/campeones-filtrorol.pipe';
import { CampeonesService } from '../services/campeones.service';

@Component({
  selector: 'campeones-list',
  templateUrl: './campeones-list.component.html',
  styleUrls: ['./campeones-list.component.css'],
})
export class CampeonesListComponent implements OnInit {
  search = '';
  searchRol = '';
  campeones!: Campeon[];

  constructor(
    private campeonesService: CampeonesService,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.campeonesService.getCampeones().subscribe((camp) => {
      this.campeones = camp;
      this.titleService.setTitle('League of Legends');
    });
  }

  deleteCampeon(campeon: Campeon): void {
    this.campeones = this.campeones.filter((c) => c !== campeon);
  }

  filtroTodos(): void {
    this.searchRol = '';
  }
  filtroAsesinos(): void {
    this.searchRol = 'Asesinos';
  }
  filtroLuchadores(): void {
    this.searchRol = 'Luchadores';
  }
  filtroMagos(): void {
    this.searchRol = 'Magos';
  }
  filtroTiradores(): void {
    this.searchRol = 'Tiradores';
  }
  filtroApoyos(): void {
    this.searchRol = 'Apoyos';
  }
  filtroTanques(): void {
    this.searchRol = 'Tanques';
  }
}
