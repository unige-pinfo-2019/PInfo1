import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams} from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Item } from '../models/Item.model';


/*Class regrouping all the services needed for posts*/
@Injectable()
export class PutService{

  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',}),
  };

  constructor(private httpClient: HttpClient){}

modifPost(newItem: Item){
  console.log("voici ce que je veux modifié " +newItem);
  this.httpClient.put(environment.items_url+'/updateitem/',newItem,this.httpOptions).subscribe(()=>{
    console.log('modifié ! ');

  },(error) => {console.log('Erreur  ! : '+ error);}
  );}

  delPost(newItem: Item){
    console.log("voici ce que je veux supprimé " +newItem);
    this.httpClient.put(environment.items_url+'/removeitem/',newItem,this.httpOptions).subscribe(()=>{
      console.log('supprimé ! ');

    },(error) => {console.log('Erreur  ! : '+ error);}
    );}



  modifAnnonce(name: string, categorie: string, description: string, etat: number){
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

    console.log("voici ce que je veux ajouter "+postAd);
    this.httpClient.post(environment.ad_url+'/',postAd,this.httpOptions).subscribe(()=>{

      console.log('Saved ! ');
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}


  modifUser(id: number, email: string, surname: string, name: string, username: string, report: number, grade: number) {
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

}
