import { TestBed } from '@angular/core/testing';

import { MyRestService } from './my-rest.service';

describe('LoginService', () => {
  let service: MyRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MyRestService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
