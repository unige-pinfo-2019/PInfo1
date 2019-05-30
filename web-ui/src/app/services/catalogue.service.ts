import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
//import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {

  baseURL: string = "http://localhost:10080/item/s/";
  baseURL_post: string = "http://localhost:11080/item/additem?usrid=1";
  baseURL_discussion: string = "http://localhost:11080/discussion/";
  baseURL_messenger: string = "http://localhost:11080/messenger/";

  private messageSource = new BehaviorSubject("?category=all");
  currentMessage = this.messageSource.asObservable();

  private messageSource_usrid = new BehaviorSubject("1234");
  currentMessage_usrid = this.messageSource_usrid.asObservable();

  page: string = "1"
  message: any;
  l_id: string[];
  private list_items: any[];

  constructor(private httpClient: HttpClient) {}

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

  get_annonce() {
    return this.httpClient.get("http://localhost:11080/annonce/allannonce")
  }

  get_item(paramid: string){
    return this.httpClient.get("http://localhost:10080/item/getitemID?id=" + paramid)
  }

  get_discussion(id: string, toid: string){
    return this.httpClient.get("http://localhost:10080/messenger/?myid=" + id+"&toid="+toid)
  }

  get_messenger(id: string){
    return this.httpClient.get("http://localhost:10080/messenger/?myid=" + id)
  }

  get_user(userid: string) {
    console.log("http://localhost:12080/user/getuserid?id=" + userid);
    console.log(this.httpClient.get("http://localhost:12080/user/getuserid?id=" + userid))
    return this.httpClient.get("http://localhost:12080/user/getuserid?id=" + userid)// + userid)
  }

  get_highlight() {
    console.log("http://localhost:14080/statistic/topitems");
    return this.httpClient.get("http://localhost:14080/statistic/topitem?nitems=6");
  }

  get_highCat(){
    console.log("http://localhost:14080/statistic/topcat?ncategories=3");
    return this.httpClient.get("http://localhost:14080/statistic/topcat?ncategories=3");
  }

  get_highCatItem(cat: string){
    console.log("http://localhost:14080/statistic/topitemcat?category="+cat+"&nitems=3");
    return this.httpClient.get("http://localhost:14080/statistic/topitemcat?category="+cat+"&nitems=3");
  }


  post_message(id: string) {
    this.httpClient.get("http://localhost:10080/messenger/addmessage?usrid="+id);
  }


  post_user(message: string) {
    //console.log(this.baseURL +message);
    //this.httpClient.put(this.baseURL_post+ message)
    console.log("post user : " +"http://localhost:10080/user/adduserusrid=1&name=ftg&price=2&category=livre&description=okok&state=3");
    this.httpClient.get("http://localhost:10080/item/additem?usrid=1&name=ftg&price=2&category=livre&description=couscous&state=2");
  }

  get_item_by_user(usrid: string){
    console.log("http://localhost:10080/item/getitem?usrid=" + usrid);
    return this.httpClient.get("http://localhost:10080/item/getitem?usrid=" + usrid)
  }


  


}
