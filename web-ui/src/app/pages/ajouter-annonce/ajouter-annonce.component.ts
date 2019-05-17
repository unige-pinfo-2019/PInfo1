import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../services/post.service'
import { CatalogueService } from '../../services/catalogue.service'

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
  description : string = "";
  categorie : string = "";
  etat : number = 1;

  message: any[];
  //@Output() messageEvent = new EventEmitter<string>();

  constructor(private postService: PostService,private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
  }

  set_description(event){
    this.description = event.target.value;
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

  onSubmitForm() {
        this.postService.addAnnonce(this.name, this.categorie, this.description, this.etat);
        //this.catalogueService.post_user("salut");
        this.router.navigate(['/home']);
  }

}
