import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CatalogueService } from '../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-page-vente',
  templateUrl: './page-vente.component.html',
  styleUrls: ['./page-vente.component.scss']
})
export class PageVenteComponent implements OnInit {
  titre : string ="";
  description : string ="";
  prix : string ="";
  categorie : string ="";
  etat : string ="";

  //@Output() messageEvent = new EventEmitter<string>();

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
  }

  selectChangeHandlerCat(event: any) {
    this.categorie = event.target.value;
    console.log(this.categorie);
  }

  sendMesage(){
    //this.messageEvent.emit("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description);
    //this.catalogueService.changeMessage("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description)
    // this.catalogueService.post_item(this.message).subscribe((res: any[]) => {
    //   this.list_items = res;
    // })
    this.catalogueService.post_item("&name="+this.titre+ "&prize="+this.prix + "&category="+this.categorie + "&description="+this.description + "&state="+this.etat)
    //this.catalogueService.post_item("?category="+this.categorie+"&state="+this.etat+"&sprize="+this.titre+"&fprize="+this.description)

  }

  selectChangeHandlerState(event: any) {
    this.etat = event.target.value;
    console.log(this.etat);
  }

  set_titre(event){
    this.titre = event.target.value;
  }

  set_description(event){
    this.description = event.target.value;
  }

  set_prix(event){
    this.prix = event.target.value;
  }





  //constructor() { }


  //ngOnInit() {
  //}

}
