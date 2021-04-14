import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Campeon } from '../interfaces/campeon';
import { Habilidad } from '../interfaces/habilidad';
import { CampeonesService } from '../services/campeones.service';
import { HabilidadesService } from '../services/habilidades.service';

@Component({
  selector: 'campeon-detail',
  templateUrl: './campeon-detail.component.html',
  styleUrls: ['./campeon-detail.component.css'],
})
export class CampeonDetailComponent implements OnInit {
  campeon!: Campeon;
  habilidades!: Habilidad[];

  constructor(
    private router: Router,
    private campeonesService: CampeonesService,
    private habilidadesService: HabilidadesService,
    private route: ActivatedRoute,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.campeonesService.getCampeon(params.id).subscribe(
        (camp) => {
          this.campeon = camp;
          this.titleService.setTitle(this.campeon.nombre);
        },
        (error) => this.router.navigate(['/campeones'])
      );
    });

    this.route.params.subscribe((params) => {
      this.habilidadesService.getHabilidadCampeon(params.id).subscribe(
        (hab) => {
          this.habilidades = hab;
        },
        (error) => this.router.navigate(['/campeones'])
      );
    });
  }

  goBack(): void {
    this.router.navigate(['/campeones']);
  }

}
