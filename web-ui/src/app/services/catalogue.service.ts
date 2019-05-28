import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';

//import { Observable } from 'rxjs/Observable';

@Injectable()
export class CatalogueService {

  baseURL: string = environment.items_url+"/item/s/";
  //baseURL_post: string = "http://localhost:11080/item/additem?usrid=1";

  private messageSource = new BehaviorSubject("?category=all");
  currentMessage = this.messageSource.asObservable();

  private messageSource_usrid = new BehaviorSubject("1234");
  currentMessage_usrid = this.messageSource_usrid.asObservable();

  private messageSource_info_usr = new BehaviorSubject("1234");
  currentMessage_info_usr = this.messageSource_info_usr.asObservable();

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

  changeInfo(info_in: string) {
    this.messageSource_info_usr.next(info_in); // same id 1234
  }

  get_catalogue(paramCat: string) {
    console.log(this.baseURL + this.page+ paramCat);
    return this.httpClient.get(this.baseURL+ this.page+ paramCat)
  }

  get_annonce() {
    return this.httpClient.get(environment.ad_url+"/annonce/allannonce")
  }

  get_item(paramid: string){
    console.log("http://localhost:10080/item/getitemID?id=" + paramid);
    return this.httpClient.get(environment.items_url+"/item/getitemID?id=" + paramid)
  }

  get_user(userid: string) {
    console.log("http://localhost:12080/user/getuserid?id=" + userid);
    console.log(this.httpClient.get(environment.user_url+"/user/getuserid?id=" + userid))
    return this.httpClient.get(environment.user_url+"/user/getuserid?id=" + userid)// + userid)
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



  post_user(message: string) {
    //console.log(this.baseURL +message);
    //this.httpClient.put(this.baseURL_post+ message)
    console.log("post user : " +"http://localhost:10080/user/adduserusrid=1&name=ftg&price=2&category=livre&description=okok&state=3");
    this.httpClient.get(environment.items_url+"/item/additem?usrid=1&name=ftg&price=2&category=livre&description=couscous&state=2");
  }

  get_item_by_user(usrid: string){
    console.log("https://localhost/api/items/item/getitem?usrid=" + usrid);
    return this.httpClient.get(environment.items_url+"/item/getitem?usrid=" + usrid)
  }


}
