import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../../services/post.service'
import { CatalogueService } from '../../../services/catalogue.service';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-modify-profil',
  templateUrl: './modify-profil.component.html',
  styleUrls: ['./modify-profil.component.scss']
})
export class ModifyProfilComponent implements OnInit {

  constructor(private catalogueService: CatalogueService, private postService: PostService, private router: Router, public keycloak: KeycloakService) { }

  postForm : FormGroup;
  id : number = 0;
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
