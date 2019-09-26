import { TestBed } from '@angular/core/testing';

import { HearthstoneService } from './hearthstone.service';

describe('HearthstoneService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HearthstoneService = TestBed.get(HearthstoneService);
    expect(service).toBeTruthy();
  });
});
