import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { HelloService } from './hello.service';

describe('HelloService', () => {
  let service: HelloService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
      providers: [ HelloService ]
    });
    service = TestBed.inject(HelloService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should returns http 200 response when call http GET method', () => {
      // arrange
      const expectedMessage = 'hello world';

      // act
      service.getHello().subscribe((message) => {
        expect(message).toEqual(expectedMessage);
      });

      // assert
      const req = httpMock.expectOne('/api/hello');
      expect(req.request.method).toEqual('GET');
      req.flush(expectedMessage);
      httpMock.verify();
  });
});
