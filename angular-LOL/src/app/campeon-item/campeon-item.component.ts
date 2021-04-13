import { Component, Input, OnInit } from '@angular/core';
import { Campeon } from '../interfaces/campeon';

@Component({
  selector: 'campeon-item',
  templateUrl: './campeon-item.component.html',
  styleUrls: ['./campeon-item.component.css']
})
export class CampeonItemComponent implements OnInit {

  @Input() campeon!:Campeon;
  constructor() { }

  ngOnInit(): void {
  }

}
