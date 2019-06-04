import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-vente',
  templateUrl: './page-vente.component.html',
  styleUrls: ['./page-vente.component.scss']
})
export class PageVenteComponent implements OnInit {
  postForm : FormGroup;
  name : string = "";
  name_boolean : boolean = false;
  description : string = "";
  description_boolean : boolean = false;
  price : number = 0;
  price_boolean : boolean = false;
  categorie : string = "";
  categorie_boolean : boolean = false;
  etat : number = 1;
  etat_boolean : boolean = false;

  message: any[];
  //@Output() messageEvent = new EventEmitter<string>();

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
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
    if(event.target.value=="0"){
      this.etat_boolean=false;
    }else{
      this.etat_boolean=true;
    }
    console.log(this.etat);
  }

  selectChangeHandlerCat(event: any) {
    this.categorie = event.target.value;
    if(event.target.value=="all"){
      this.categorie_boolean=false;
    }else{
      this.categorie_boolean=true;
    }
    console.log(this.categorie);
  }

  set_description(event){
    this.description = event.target.value;
    if(event.target.value==""){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  set_prix(event){
    this.price = event.target.value;
    if(event.target.value==""){
      this.price_boolean=false;
    }else{
      this.price_boolean=true;
    }
    console.log(this.price);

  }

  test() {
    console.log("envoyé")
    //this.router.navigate('http://localhost:10080/item/additem?usrid=1234&name=ftgew&prize=2&category=livre&description=okok&state=3')
  }

  onSubmitForm() {

        this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat);
        //this.catalogueService.post_user("salut");
        console.log(this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat));
        this.router.navigate(['/profil']);
}
}
