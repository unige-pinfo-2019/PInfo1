import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../../services/post.service'
import { CatalogueService } from '../../../services/catalogue.service';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router';
import { Image } from '../../../models/Item.model';
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';



@Component({
  selector: 'app-modify-profil',
  templateUrl: './modify-profil.component.html',
  styleUrls: ['./modify-profil.component.scss']
})
export class ModifyProfilComponent implements OnInit {

  constructor(private catalogueService: CatalogueService, private postService: PostService, private httpClient: HttpClient, private router: Router, public keycloak: KeycloakService) { }

  postForm : FormGroup;
  id : number = 0;
  image: string = "Acq7kkx";

  email : string = "";
  email_boolean : boolean = false;
  last_name : string = "";
  last_name_boolean : boolean = false;
  first_name : string = "";
  first_name_boolean : boolean = false;
  user_name : string= "";
  user_name_boolean : boolean = false;

  message: string = "1234";
  info_user: any = {"id": "", "first_name": "","last_name": "","user_name": "","email": ""};

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

  onFIleSelected(event){
      this.selectedFile = event.target.files[0]
  }

  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name)
    this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
      this.image = res.data.id;
      },(error) => {console.log('Erreur  ! : '+ error);}
    );
  }

  ngOnInit() {
    this.id = this.keycloak.getKeycloakAuth().subject;
    this.first_name = this.keycloak.getFirstName();
    this.last_name = this.keycloak.getLastName();
    this.user_name = this.keycloak.getUsername();
    this.email = this.keycloak.getEmail();
}

  newInfo() {
    this.info_user.first_name = this.first_name;
    this.info_user.last_name = this.last_name;
    this.info_user.user_name = this.user_name;
    this.info_user.email = this.email;
  }

  set_first_name(event) {
    this.first_name = event.target.value;
    if(event.target.value=="0"){
      this.first_name_boolean=false;
    }else{
      this.first_name_boolean=true;
    }
  }

  set_last_name(event){
    this.last_name = event.target.value;
    if(event.target.value=="0"){
      this.last_name_boolean=false;
    }else{
      this.last_name_boolean=true;
    }
  }

  set_user_name(event){
    this.user_name = event.target.value;
    if(event.target.value=="0"){
      this.user_name_boolean=false;
    }else{
      this.user_name_boolean=true;
    }
  }

  set_email(event){
    this.email = event.target.value;
    if(event.target.value==""){
      this.email_boolean=false;
    }else{
      this.email_boolean=true;
    }
  }

  onSubmitForm() {
    this.keycloak.setFirstName(this.info_user.first_name);
    this.keycloak.setLastName(this.info_user.last_name);
    this.keycloak.setUserName(this.info_user.user_name);
    this.keycloak.setEmail(this.info_user.email);
    this.router.navigate(['../profil/info']);
  }
}
