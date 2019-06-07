import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Observable, Subscription } from 'rxjs';
import { Image } from '../../../models/Item.model';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';



@Component({
  selector: 'app-info-perso',
  templateUrl: './info-perso.component.html',
  styleUrls: ['./info-perso.component.scss']
})
export class InfoPersoComponent implements OnInit {

  //info_user: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};
  info_user: any = {"id": "", "first_name": "","last_name": "","user_name": "","email": ""};
  message: string = "1234";
  image: string = "";

  selectedFile = null;

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };


  onFIleSelected(event){
      this.selectedFile = event.target.files[0]
      console.log(this.selectedFile);
  }

  onUpload(){
    // const fd = new FormData();
    // fd.append('image', this.selectedFile, this.selectedFile.name)
    // this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
    //   console.log('image Saved ! ');
    //   console.log(res);
    //   console.log(res.data.id);
    //   this.image = res.data.id;  // Specifier où doit être mis l'image !!!!!!!
    //   },(error) => {console.log('Erreur  ! : '+ error);}
    // );
  }


  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService, private httpClient: HttpClient) { }

  ngOnInit() {

    /*
    this.catalogueService.currentMessage_info_usr.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_user(this.message).subscribe((res: any[]) => {
      this.info_user = res[0];
      console.log(this.info_user);
  }
)
}
)*/

  this.info_user.id = this.keycloak.getKeycloakAuth().subject;
  this.info_user.first_name = this.keycloak.getFirstName();
  this.info_user.last_name = this.keycloak.getLastName();
  this.info_user.user_name = this.keycloak.getUsername();
  this.info_user.email = this.keycloak.getEmail();
  console.log(this.info_user);

  }

}
