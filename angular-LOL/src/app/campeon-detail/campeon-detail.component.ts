import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { Campeon } from '../interfaces/campeon';
import { CampeonesService } from '../services/campeones.service';

@Component({
  selector: 'campeon-detail',
  templateUrl: './campeon-detail.component.html',
  styleUrls: ['./campeon-detail.component.css'],
})
export class CampeonDetailComponent implements OnInit {
  campeon!: Campeon;

  constructor(
    private router: Router,
    private campeonesService: CampeonesService,
    private route: ActivatedRoute,
    private titleService: Title
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.campeonesService.getCampeon(params.id).subscribe(
        (camp) => {
          this.campeon = camp;
          //this.titleService.setTitle('Angular Products | ' + this.campeon.nombre);
          console.log(this.campeon.nombre);
        },
        (error) => this.router.navigate(['/campeones'])
      );
    });
  }
}
