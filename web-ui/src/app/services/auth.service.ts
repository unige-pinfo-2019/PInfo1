import {Subject} from 'rxjs';
import {Injectable} from '@angular/core';
import { HttpHeaders, HttpClient ,HttpParams} from '@angular/common/http';

@Injectable()
export class AuthService {
  model: any= {};
  isAuth = false;
  list: any[];

  signIn() {
    return new Promise(
      (resolve, reject) => {
        setTimeout(
          () => {
            this.isAuth = true;
            console.log(this.isAuth);
            resolve(true);
          }, 2000
        );
      }
    );
  }




  signOut() {
    this.isAuth = false;
  }

  display() {
    /*this.httpClient.get<any[]>('http://localhost:8080/s/1', {responseType: 'text'}).subscribe((response) => {
      this.list = response;
      console.log('laaa'+ this.list)  ;
      return this.list;

    }
  )*/
  return 0

  }

  constructor(private httpClient: HttpClient) {
    console.log(httpClient)
  }

}
