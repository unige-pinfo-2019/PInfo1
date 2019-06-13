import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service';
import { PutService } from '../../../services/put.service';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Observable, Subscription } from 'rxjs';
import { Image, User } from '../../../models/Item.model';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';



@Component({
  selector: 'app-info-perso',
  templateUrl: './info-perso.component.html',
  styleUrls: ['./info-perso.component.scss']
})
export class InfoPersoComponent implements OnInit {
  info_user: any = {"id": "", "first_name": "","last_name": "","user_name": "","email": ""};
  image: string = "Acq7kkx";
  us: User;
  selectedFile = null;

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };

  public imagePath;
  imgURL: any;
  public mes: string;

  preview(files) {
    if (files.length === 0)
      return;

    var mimeType = files[0].type;
    if (mimeType.match(/image\/*/) == null) {
      this.mes = "Only images are supported.";
      return;
    }

    var reader = new FileReader();
    this.imagePath = files;
    reader.readAsDataURL(files[0]);
    reader.onload = (_event) => {
      this.imgURL = reader.result;
    }
    this.selectedFile = files[0];

  }


  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name)
    this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
      this.image = res.data.id;
      this.us.image = this.image;
      this.putService.modifImageUser(this.keycloak.getKeycloakAuth().subject,this.image);
      window.location.reload()
      },(error) => {console.log('Erreur  ! : '+ error);
    }
    );
  }


  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService, private httpClient: HttpClient, private putService: PutService) { }

  ngOnInit() {

    this.catalogueService.get_user(this.keycloak.getKeycloakAuth().subject).subscribe((res: User) => {
        this.us = res;
        if (this.us.image != "" && this.us.image != null){
          console.log(this.us.image);
          this.image = this.us.image;
        }
  });

  this.info_user.id = this.keycloak.getKeycloakAuth().subject;
  this.info_user.first_name = this.keycloak.getFirstName();
  this.info_user.last_name = this.keycloak.getLastName();
  this.info_user.user_name = this.keycloak.getUsername();
  this.info_user.email = this.keycloak.getEmail();
  console.log(this.info_user);

  }

}
