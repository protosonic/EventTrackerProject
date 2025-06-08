import { TestBed } from '@angular/core/testing';

import { BountyService } from './bounty-service';

describe('BountyService', () => {
  let service: BountyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BountyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
