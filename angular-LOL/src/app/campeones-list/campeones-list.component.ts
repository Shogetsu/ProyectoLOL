import { Component, OnInit } from '@angular/core';
import { CampeonesAddComponent } from '../campeones-add/campeones-add.component';
import { Campeon } from '../interfaces/campeon';
import { CampeonesFiltrorolPipe } from '../pipes/campeones-filtrorol.pipe';
import { CampeonesService } from '../services/campeones.service';

@Component({
  selector: 'campeones-list',
  templateUrl: './campeones-list.component.html',
  styleUrls: ['./campeones-list.component.css']
})
export class CampeonesListComponent implements OnInit {

  search='';
  searchRol='';
  campeones!:Campeon[];

  constructor(private campeonesService:CampeonesService) { }

  ngOnInit(): void {
    this.campeonesService.getCampeones().subscribe(
      camp=> this.campeones = camp
    );
  }

  deleteCampeon(campeon:Campeon):void{
    this.campeones= this.campeones.filter(c=>c!==campeon);
  }

  filtroAsesinos():void{
    this.searchRol='Asesinos';
  }

}
