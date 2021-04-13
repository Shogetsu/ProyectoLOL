import { TestBed } from '@angular/core/testing';

import { CampeonesService } from './campeones.service';

describe('CampeonesService', () => {
  let service: CampeonesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CampeonesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
