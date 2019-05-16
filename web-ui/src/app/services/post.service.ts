import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams} from '@angular/common/http';

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

  console.log('Enregistrement en cours... ');
  this.httpClient.post('http://localhost:10080/item/',postObject,this.httpOptions).subscribe(()=>{
    console.log('Enregistrement terminé ! ');
  },(error) => {console.log('Erreur  ! : '+ error);}
  );}




  addAnnonce(name: string, price: number, categorie: string, description: string, etat: number){
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

    console.log('Enregistrement en cours... ');
    this.httpClient.post('http://localhost:10080/item/',postObject,this.httpOptions).subscribe(()=>{
      console.log('Enregistrement terminé ! ');
    },(error) => {console.log('Erreur  ! : '+ error);}
    );}

}
