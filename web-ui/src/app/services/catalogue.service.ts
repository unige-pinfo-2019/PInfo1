import { Subject } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {

  list: any[];

  displayCatalogue() {
    this.httpClient.get<any[]>('http://localhost:8080/s/1', {responseType: 'text'}).subscribe((response) => {
      this.list = response;
      console.log('la'+this.list);
      return this.list;
    }
  )





  }


  constructor(private httpClient: HttpClient) {
    console.log(httpClient)
  }

}
