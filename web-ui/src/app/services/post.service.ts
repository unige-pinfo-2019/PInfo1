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

addPost(name: string, prize: string, categorie: string, description: string, etat: string){
  const postObject = {
    // id: 1234,
    // name: "",
    // surname: "",
    // username: "",
    // email: "",
    // report: 0,

    usrId: 1234,
    name: "",
    prize: "",
    category: "",
    description: "",
    state: 0,
  }

  postObject.name = name;
  postObject.prize = prize;
  postObject.category = categorie;
  postObject.description = description;
  postObject.state = etat;


  //this.posts.push(postObject);
  console.log(postObject);
  //this.printPosts();
  //this.emitPostSubject();
  console.log(postObject);

  console.log('Enregistrement en cours... ');
  this.httpClient
  .post('http://localhost:10080/item/',
  postObject,this.httpOptions).subscribe(
  ()=>{
    console.log('Enregistrement terminé ! ');
  },(error) => {
    console.log('Erreur  ! : '+ error);
  }

);
}

}
