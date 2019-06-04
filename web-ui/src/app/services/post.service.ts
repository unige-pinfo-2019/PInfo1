import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams} from '@angular/common/http';
import { environment } from '../../environments/environment';


/*Class regrouping all the services needed for posts*/
@Injectable()
export class PostService{

  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',}),
  };

  constructor(private httpClient: HttpClient){}

addPost(name: string, price: number, categorie: string, description: string, etat: number){
  const postObject = {
    usrId: 1234,
    name: "",
    price: 0,
    category: "",
    description: "",
    state: 0,
  }

  postObject.name = name;
  postObject.price = price;
  postObject.category = categorie;
  postObject.description = description;
  postObject.state = etat;

  console.log(postObject);
  this.httpClient.post(environment.items_url+'/',postObject,this.httpOptions).subscribe(()=>{
    console.log('Saved ! ');

  },(error) => {console.log('Erreur  ! : '+ error);}
  );}

  addUser(email: string, password: string, surname: string, lastname: string, username: string){
    const postUser = {
      name: "",
      surname: "",
      username: "",
      email: ""
    }

    postUser.name = lastname;
    postUser.surname = surname;
    postUser.username = username;
    postUser.email = email;

    console.log(postUser);
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

      this.httpClient.post('http://localhost:13080/messenger/',postMsg,this.httpOptions).subscribe(()=>{
        console.log('Saved ! ');
      },(error) => {console.log('Erreur  ! : '+ error);}
      );}


  addAnnonce(name: string, categorie: string, description: string, etat: number){
    const postAd = {
      usrId: 1234,
      name: "",
      category: "",
      description: "",
      state: 0,
    }

    postAd.name = name;
    postAd.category = categorie;
    postAd.description = description;
    postAd.state = etat;

    console.log(postAd);
    this.httpClient.post(environment.ad_url+'/',postAd,this.httpOptions).subscribe(()=>{

      console.log('Saved ! ');
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

      console.log("putUser: "+ putUser.name);
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

      console.log("putItem: "+ putItem.usrId);
      this.httpClient.put(environment.items_url+'/',putItem,this.httpOptions).subscribe(()=>{
        console.log('Modified ! ');
      },(error) => {console.log('Erreur  ! : '+ error);}
      );
    }
}
