import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';


import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-ajouter-annonce',
  templateUrl: './ajouter-annonce.component.html',
  styleUrls: ['./ajouter-annonce.component.scss']
})
export class AjouterAnnonceComponent implements OnInit {
  postForm : FormGroup;
  name : string = "";
  name_boolean : boolean = false;
  description : string = "";
  description_boolean : boolean = false;
  categorie : string = "";
  categorie_boolean : boolean = false;
  etat : string = "";
  etat_boolean : boolean = false;
  first_name: string =  this.keycloak.getFirstName();
  last_name: string =  this.keycloak.getLastName();
  email : string = this.keycloak.getEmail();

  message: any[];

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router, public keycloak: KeycloakService) { }

  ngOnInit() {
  }

  set_description(event){
    this.description = event.target.value;
    if(event.target.value==""){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  set_name(event){
    this.name = event.target.value;
    if(event.target.value==""){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  selectChangeHandlerState(event: any) {
    this.etat = event.target.value;
    if(event.target.value==""){
      this.etat_boolean=false;
    }else{
      this.etat_boolean=true;
    }
  }

  selectChangeHandlerCat(event: any) {
    this.categorie = event.target.value;
    if(event.target.value=="all"){
      this.categorie_boolean=false;
    }else{
      this.categorie_boolean=true;
    }
  }

  onSubmitForm() {
        this.postService.addAnnonce(this.name, this.categorie, this.description, this.etat,this.first_name, this.last_name, this.email);
        //this.catalogueService.post_user("salut");
        window.location.reload();
        this.router.navigate(['/profil/annonce']);

  }

}
