import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampeonesAddComponent } from './campeones-add.component';

describe('CampeonesAddComponent', () => {
  let component: CampeonesAddComponent;
  let fixture: ComponentFixture<CampeonesAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampeonesAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampeonesAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
