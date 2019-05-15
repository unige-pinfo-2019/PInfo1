import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'

import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-annonce',
  templateUrl: './annonce.component.html',
  styleUrls: ['./annonce.component.scss']
})
export class AnnonceComponent implements OnInit {
  postForm : FormGroup;
  name : string = "";
  description : string = "";
  price : string = "";
  categorie : string = "";
  etat : string = "";

  message: any[];
  //@Output() messageEvent = new EventEmitter<string>();

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
  }

  set_description(event){
    this.description = event.target.value;
  }

  set_prix(event){
    this.price = event.target.value;
  }

  test() {
    console.log("envoyé")
    //this.router.navigate('http://localhost:10080/item/additem?usrid=1234&name=ftgew&prize=2&category=livre&description=okok&state=3')
  }

  onSubmitForm() {

        this.postService.addPost("this.name", this.price, this.categorie, this.description, this.etat);
        //this.catalogueService.post_user("salut");

        this.router.navigate(['/catalogue']);
  }

}

//   constructor() { }
//
//   ngOnInit() {
//   }
//
// }
