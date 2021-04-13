import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CampeonesListComponent } from './campeones-list.component';

describe('CampeonesListComponent', () => {
  let component: CampeonesListComponent;
  let fixture: ComponentFixture<CampeonesListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CampeonesListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CampeonesListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
