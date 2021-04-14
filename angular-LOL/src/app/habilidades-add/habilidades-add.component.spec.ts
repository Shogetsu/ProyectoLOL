import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HabilidadesAddComponent } from './habilidades-add.component';

describe('HabilidadesAddComponent', () => {
  let component: HabilidadesAddComponent;
  let fixture: ComponentFixture<HabilidadesAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HabilidadesAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HabilidadesAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
