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
  this.httpClient.post(environment.items_url+'/item/',postObject,this.httpOptions).subscribe(()=>{
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
    this.httpClient.post('http://localhost:12080/user/',postUser,this.httpOptions).subscribe(()=>{
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
    this.httpClient.post(environment.ad_url+'/annonce/',postAd,this.httpOptions).subscribe(()=>{
      console.log('Saved ! ');
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}

}
