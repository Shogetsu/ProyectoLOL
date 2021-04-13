import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampeonDetailComponent } from './campeon-detail.component';

describe('CampeonDetailComponent', () => {
  let component: CampeonDetailComponent;
  let fixture: ComponentFixture<CampeonDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampeonDetailComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampeonDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
