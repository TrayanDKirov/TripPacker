import { TestBed } from '@angular/core/testing';

import { PackingItemService } from './packing-item.service';

describe('PackingItemPackingItem', () => {
  let service: PackingItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PackingItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
