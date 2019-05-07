import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {

  baseURL: string = "http://localhost:8080/item/s/1";

  private messageSource = new BehaviorSubject("?category=all");
  currentMessage = this.messageSource.asObservable();

  constructor(private httpClient: HttpClient) {}

  /*
  displayCatalogue() {
    this.httpClient.get<any[]>('http://localhost:8080/s/1', {responseType: 'text'}).subscribe((response) => {
      this.list = response;
      console.log('la'+this.list);
      return this.list;
    }
  )

  }
  */

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  get_catalogue(paramCat: string) {
    console.log(this.baseURL + paramCat);
    return this.httpClient.get(this.baseURL+ paramCat)
  }


}
