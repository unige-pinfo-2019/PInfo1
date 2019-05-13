import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {
  tmp : string = "";
  baseURL: string = "http://localhost:8080/item/s/1";
  baseURL_post: string = "http://localhost:8080/item/additem?usrid=1";

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



  post_item(message: string) {
    //console.log(this.baseURL +message);
    //this.httpClient.put(this.baseURL_post+ message)
    tmp = this.httpClient.get("http://localhost:8080/item/additem?usrid=1&name=okokokok&prize=12&category=livre&description=okok&state=3")
  }


}
