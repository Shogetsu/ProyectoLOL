import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Campeon } from '../interfaces/campeon';
import { CampeonesService } from '../services/campeones.service';

@Component({
  selector: 'campeon-item',
  templateUrl: './campeon-item.component.html',
  styleUrls: ['./campeon-item.component.css']
})
export class CampeonItemComponent implements OnInit {

  @Input() campeon!:Campeon;
  @Output() deletedCampeon = new EventEmitter<void>();

  constructor(private campeonesService:CampeonesService) { }

  ngOnInit(): void {
  }

  deleteCampeon():void{
    this.campeonesService.deleteCampeon(this.campeon.idCampeon!).subscribe(
      () => this.deletedCampeon.emit(),
      error => alert('No se puede borrar un campo que está referenciado en otra tabla') // Error function (optional)
    )
  }

  modificarCampeon():void{

  }

}
