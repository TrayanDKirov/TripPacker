import { TestBed } from '@angular/core/testing';

import { GroupToPack } from './group-to-pack';

describe('GroupToPack', () => {
  let service: GroupToPack;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroupToPack);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
