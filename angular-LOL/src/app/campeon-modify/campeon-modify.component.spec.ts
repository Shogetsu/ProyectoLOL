import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampeonModifyComponent } from './campeon-modify.component';

describe('CampeonModifyComponent', () => {
  let component: CampeonModifyComponent;
  let fixture: ComponentFixture<CampeonModifyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampeonModifyComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampeonModifyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
