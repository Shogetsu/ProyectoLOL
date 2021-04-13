import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampeonItemComponent } from './campeon-item.component';

describe('CampeonItemComponent', () => {
  let component: CampeonItemComponent;
  let fixture: ComponentFixture<CampeonItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampeonItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampeonItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
