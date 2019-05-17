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
  description : string = "";
  price : number = 0;
  categorie : string = "";
  etat : number = 1;

  message: any[];
  //@Output() messageEvent = new EventEmitter<string>();

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
  }


  set_name(event){
    this.name = event.target.value;
  }

  selectChangeHandlerState(event: any) {
    this.etat = event.target.value;
    console.log(this.etat);
  }

  selectChangeHandlerCat(event: any) {
    this.categorie = event.target.value;
    console.log(this.categorie);
  }

  set_description(event){
    this.description = event.target.value;
  }

  set_prix(event){
    this.price = event.target.value;
    console.log(this.price);

  }

  test() {
    console.log("envoyé")
    //this.router.navigate('http://localhost:10080/item/additem?usrid=1234&name=ftgew&prize=2&category=livre&description=okok&state=3')
  }

  onSubmitForm() {

        this.postService.addPost(this.name, this.price, this.categorie, this.description, this.etat);
        //this.catalogueService.post_user("salut");

        this.router.navigate(['/profil']);
}
}
