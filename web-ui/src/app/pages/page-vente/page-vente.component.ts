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

  // selectChangeHandlerCat(event: any) {
  //   this.categorie = event.target.value;
  //   console.log(this.categorie);
  // }
  //
  // sendMesage(){
  //   //this.messageEvent.emit("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description);
  //   //this.catalogueService.changeMessage("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description)
  //   // this.catalogueService.post_item(this.message).subscribe((res: any[]) => {
  //   //   this.list_items = res;
  //   // })
  //   this.catalogueService.post_item("&name="+this.titre+ "&prize="+this.prix + "&category="+this.categorie + "&description="+this.description + "&state="+this.etat).subscribe((res: any[]) => {
  //     this.message = res;
  //     console.log(this.message)
  //     })
  //   //this.catalogueService.post_item("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description)
  //
  // }
  //
  // selectChangeHandlerState(event: any) {
  //   this.etat = event.target.value;
  //   console.log(this.etat);
  // }
  //
  // set_titre(event){
  //   this.titre = event.target.value;
  // }
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

        this.router.navigate(['/catalogue']);
}



  //constructor() { }


  //ngOnInit() {
  //}

}
