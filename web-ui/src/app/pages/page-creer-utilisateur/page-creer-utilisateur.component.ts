import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-creer-utilisateur',
  templateUrl: './page-creer-utilisateur.component.html',
  styleUrls: ['./page-creer-utilisateur.component.scss']
})
export class PageCreerUtilisateurComponent implements OnInit {

  postForm : FormGroup;
  email : string = "";
  email_boolean : boolean = false;
  password : string = "";
  password_boolean : boolean = false;
  surname : string = "";
  surname_boolean : boolean = false;
  lastname : string = "";
  lastname_boolean : boolean = false;
  username : string= "";
  username_boolean : boolean = false;

  message: any[];

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
  }


  set_email(event){
    this.email = event.target.value;
    if(event.target.value==""){
      this.email_boolean=false;
    }else{
      this.email_boolean=true;
    }
  }


  set_password(event){
    this.password = event.target.value;
    if(event.target.value==""){
      this.password_boolean=false;
    }else{
      this.password_boolean=true;
    }
  }


  set_surname(event){
    this.surname = event.target.value;
    if(event.target.value=="0"){
      this.surname_boolean=false;
    }else{
      this.surname_boolean=true;
    }
  }


  set_lastname(event){
    this.lastname = event.target.value;
    if(event.target.value=="0"){
      this.lastname_boolean=false;
    }else{
      this.lastname_boolean=true;
    }
  }




  set_username(event){
    this.username = event.target.value;
    if(event.target.value=="0"){
      this.username_boolean=false;
    }else{
      this.username_boolean=true;
    }
  }





  onSubmitForm() {

        //this.postService.addUser(this.email, this.password, this.surname, this.lastname, this.username);
        this.router.navigate(['/profil']);
}
}
