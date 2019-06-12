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
  info_user: any = {"id": "", "first_name": "","last_name": "","user_name": "","email": ""};
  image: string = "";

  selectedFile = null;

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };


  onFIleSelected(event){
      this.selectedFile = event.target.files[0]
  }

  onUpload(){
  }


  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService, private httpClient: HttpClient) { }

  ngOnInit() {

  this.info_user.id = this.keycloak.getKeycloakAuth().subject;
  this.info_user.first_name = this.keycloak.getFirstName();
  this.info_user.last_name = this.keycloak.getLastName();
  this.info_user.user_name = this.keycloak.getUsername();
  this.info_user.email = this.keycloak.getEmail();
  console.log(this.info_user);

  }

}
