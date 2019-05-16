import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {

  baseURL: string = "http://localhost:10080/item/s/";
  baseURL_post: string = "http://localhost:11080/item/additem?usrid=1";

  private messageSource = new BehaviorSubject("?category=all");
  currentMessage = this.messageSource.asObservable();

  page: string = "1"
  message: any;
  l_id: string[];
  private list_items: any[];

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
  changePage(mes: string){
    this.page = mes;
  }

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  get_catalogue(paramCat: string) {
    console.log(this.baseURL + this.page+ paramCat);
    return this.httpClient.get(this.baseURL+ this.page+ paramCat)
  }

  get_item(paramid: string){
    console.log("http://localhost:10080/item/getitemID?id=" + paramid);
    return this.httpClient.get("http://localhost:10080/item/getitemID?id=" + paramid)
  }

  get_user(userid: string) {
    console.log("http://localhost:12080/user/getuserid?id=" + userid);
    console.log(this.httpClient.get("http://localhost:12080/user/getuserid?id=" + userid))
    return this.httpClient.get("http://localhost:12080/user/getuserid?id=" + userid)// + userid)
  }

  get_highlight() {
    console.log("http://localhost:14080/statistic/topitems");
    return this.httpClient.get("http://localhost:14080/statistic/topitems");
  }



  post_user(message: string) {
    //console.log(this.baseURL +message);
    //this.httpClient.put(this.baseURL_post+ message)
    console.log("post user : " +"http://localhost:10080/user/adduserusrid=1&name=ftg&price=2&category=livre&description=okok&state=3");
    this.httpClient.get("http://localhost:10080/item/additem?usrid=1&name=ftg&price=2&category=livre&description=couscous&state=2");
  }


}
