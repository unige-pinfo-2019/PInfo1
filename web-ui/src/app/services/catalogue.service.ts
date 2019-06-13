import { Subject, BehaviorSubject, Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { KeycloakService } from './keycloak/keycloak.service';


@Injectable()
export class CatalogueService {

  baseURL: string = environment.items_url+"/s/";

  private messageSource = new BehaviorSubject("?category=all&state=all");
  currentMessage = this.messageSource.asObservable();

  private messageSource_usrid = new BehaviorSubject(this.keycloak.getKeycloakAuth().subject);
  currentMessage_usrid = this.messageSource_usrid.asObservable();

  private messageSource_info_usr = new BehaviorSubject("1234");
  currentMessage_info_usr = this.messageSource_info_usr.asObservable();

  private messageSource_info_item = new BehaviorSubject("1234");
  currentMessage_info_item = this.messageSource_info_item.asObservable();

  page: string = "1"
  message: any;
  l_id: string[];
  private list_items: any[];

  constructor(private httpClient: HttpClient, public keycloak: KeycloakService) {}

  changePage(mes: string){
    this.page = mes;
  }

  changeMessage(message: string) {
    this.messageSource.next(message);
  }

  changeInfo(info_in: string) {
    this.messageSource_info_usr.next(info_in);
  }

  changeItem(info_in: string) {
    this.messageSource_info_item.next(info_in);
  }

  get_catalogue(paramCat: string) {
    return this.httpClient.get(this.baseURL+ this.page+ paramCat)
  }

  get_annonce() {
    return this.httpClient.get(environment.ad_url+"/allannonce")
  }

  get_item(paramid: string, currentid: string){

    return this.httpClient.get(environment.items_url+"/getitemID?id=" + paramid + "&currentid=" + currentid)
  }

  get_messenger(myid: string){
    return this.httpClient.get(environment.messenger_url+"/getinfo?usrId="+myid)
  }

  get_discussion(myId: string, hisId: string){
    return this.httpClient.get(environment.messenger_url+"/getmessenger?sendId=" + myId + "&receiveId="+hisId)
  }

  get_user(userid: string) {
    return this.httpClient.get(environment.user_url+"/getuser?id=" + userid)// + userid)
  }

  get_highlight() {
    return this.httpClient.get(environment.statistic_url+"/topitems");
  }

  get_highCat(){
    return this.httpClient.get(environment.statistic_url+"/topcat?ncategories=3");
  }

  get_topUserCat(usrid: string){
    return this.httpClient.get(environment.statistic_url+"/topusercat?usrid="+usrid+"&ncategories=3");
  }

  get_highCatItem(cat: string){
    return this.httpClient.get(environment.statistic_url+"/topitemcat?category="+cat+"&nitems=3");
  }

  // post_message(id: string) {
  //   this.httpClient.get(environment.messenger_url+"/addmessage?usrid="+id);
  // }

  // post_user(message: string) {
  //   this.httpClient.get(environment.items_url+"/additem?usrid=1&name=ftg&price=2&category=livre&description=couscous&state=2");
  // }

  get_item_by_user(usrid: string){
    return this.httpClient.get(environment.items_url+"/getitem?usrid=" + usrid)
  }

  get_annonce_by_user(usrid: string){
    return this.httpClient.get(environment.ad_url+"/getannonce?usrid=" + usrid)
  }

  get_annonce_by_id(id: string){
    return this.httpClient.get(environment.ad_url+"/extractannonce?annonceId=" + id)
  }
}
