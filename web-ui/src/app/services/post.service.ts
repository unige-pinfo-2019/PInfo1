import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams} from '@angular/common/http';
import { environment } from '../../environments/environment';
import { KeycloakService } from './keycloak/keycloak.service';


/*Class regrouping all the services needed for posts*/
@Injectable()
export class PostService{

  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',}),
  };

  constructor(private httpClient: HttpClient, public keycloak: KeycloakService){}

  addPost(name: string, price: number, categorie: string, description: string, etat: number, image: string, first_name_seller: string, last_name_seller: string, email: string){
    const postObject = {
      usrId: this.keycloak.getKeycloakAuth().subject,
      name: "",
      price: 0,
      category: "",
      description: "",
      state: 0,
      images: ""
    }

    postObject.name = name;
    postObject.price = price;
    postObject.category = categorie;
    postObject.description = description;
    postObject.state = etat;
    postObject.images = image;


    this.httpClient.post(environment.items_url+'/',postObject,this.httpOptions).subscribe(()=>{
      console.log('Saved ! ');
      console.log('->'+postObject.usrId);
      this.addUser(email,first_name_seller,last_name_seller);
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}

  addUser(email: string, name: string, surname: string){
    const postUser = {
      id: this.keycloak.getKeycloakAuth().subject,
      name: "",
      surname: "",
      email: "",
      image: "Acq7kkx",
      report: 0,
      userReport: ""
    }

    postUser.name = name;
    postUser.surname = surname;
    postUser.email = email;

    this.httpClient.post(environment.user_url+'/',postUser,this.httpOptions).subscribe(()=>{
      console.log('Saved ! ');
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}



    addMessage(message: string, send: string, receive: string){
      const postMsg = {
        msg: "",
        sendId: "",
        receiveId: ""
      }

      postMsg.msg = message;
      postMsg.sendId = send;
      postMsg.receiveId = receive;
      this.httpClient.post(environment.messenger_url,postMsg,this.httpOptions).subscribe(()=>{
        console.log('Saved ! ');
      },(error) => {console.log('Erreur  ! : '+ error);}
      );}


  addAnnonce(name: string, categorie: string, description: string, etat: string, first_name_seller: string, last_name_seller: string, email: string){
    const postAd = {
      usrId: this.keycloak.getKeycloakAuth().subject,
      name: "",
      category: "",
      description: "",
      state: "",
    }

    postAd.name = name;
    postAd.category = categorie;
    postAd.description = description;
    postAd.state = etat;
    this.httpClient.post(environment.ad_url+'/',postAd,this.httpOptions).subscribe(()=>{

      console.log('Saved ! ');
      this.addUser(email,first_name_seller,last_name_seller);
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}


    modifyUser(id: number, email: string, surname: string, name: string, username: string, report: number, grade: number) {
      const putUser = {
        id: 1234,
        name: "",
        surname: "",
        username: "",
        email: "",
        report: 0,
        grade: 0
      }

      putUser.name = name;
      putUser.surname = surname;
      putUser.username = username;
      putUser.email = email;
      putUser.report = report;
      putUser.grade = grade;

      this.httpClient.put(environment.user_url+'/',putUser,this.httpOptions).subscribe(()=>{
        console.log('Modified ! ');
      },(error) => {console.log('Erreur  ! : '+ error);}
      );
    }


    modifyItem(usrId: number, name: string, price: number, categorie: string, description: string, etat: number){
      const putItem = {
        usrId: 1234,
        name: "",
        price: 0,
        categorie: "",
        description: "",
        etat: 0,
      }

      putItem.name = name;
      putItem.price = price;
      putItem.categorie = categorie;
      putItem.description = description;
      putItem.etat = etat;

      this.httpClient.put(environment.items_url+'/',putItem,this.httpOptions).subscribe(()=>{
        console.log('Modified ! ');
      },(error) => {console.log('Erreur  ! : '+ error);}
      );
    }
}
