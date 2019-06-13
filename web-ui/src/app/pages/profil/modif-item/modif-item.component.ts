import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PutService } from '../../../services/put.service'
import { CatalogueService } from '../../../services/catalogue.service'
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { Image, Item } from '../../../models/Item.model';
import { Router } from '@angular/router'
import { KeycloakService } from '../../../services/keycloak/keycloak.service';


@Component({
  selector: 'app-modif-item',
  templateUrl: './modif-item.component.html',
  styleUrls: ['./modif-item.component.scss']
})
export class ModifItemComponent implements OnInit {


  postForm : FormGroup;
  item: Item = {"id": "", "usrId": "","name": "","price": 0,"category": "","description": "",
          "state": "","images": "","report": 0,"date": 0,"sold": false};
  id: string;
  changeItem: Item = this.item;

  name_boolean : boolean = true;
  description_boolean : boolean = true;
  price_boolean : boolean = true;
  categorie_boolean : boolean = true;
  etat_boolean : boolean = true;

  selectedFile = null;

  httpOptions = {
    headers: new HttpHeaders({
        'Authorization': 'Client-ID 4fb3f8deeec4486',}),
  };

  constructor(private catalogueService: CatalogueService, private putService: PutService, private router: Router, private httpClient: HttpClient, public keycloak: KeycloakService) { }


  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    console.log(this.id);
    this.catalogueService.get_item(this.id,this.keycloak.getKeycloakAuth().subject).subscribe((res: Item[]) => {
      this.item = res[0];
      this.changeItem = res[0];
      console.log("item à modifie : " +this.item);
    });
  }

  onUpload(){
    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name)
    this.httpClient.post('https://api.imgur.com/3/image',fd, this.httpOptions).subscribe((res: Image)=>{
      this.changeItem.images = res.data.id;
      },(error) => {console.log('Erreur  ! : '+ error);}
    );
  }

  set_name(event){
    this.changeItem.name = event.target.value;
    if(event.target.value==""){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  selectChangeHandlerState(event: any) {
    this.changeItem.state = event.target.value;
    if(event.target.value=="0"){
      this.etat_boolean=false;
    }else{
      this.etat_boolean=true;
    }
  }

  selectChangeHandlerCat(event: any) {
    this.changeItem.category = event.target.value;
    if(event.target.value=="all"){
      this.categorie_boolean=false;
    }else{
      this.categorie_boolean=true;
    }
  }

  set_description(event){
    this.changeItem.description = event.target.value;
    if(event.target.value==""){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  set_prix(event){
    this.changeItem.price = event.target.value;
    if(event.target.value==""){
      this.price_boolean=false;
    }else{
      this.price_boolean=true;
    }
  }

  onSubmitForm() {
    this.putService.modifPost(this.changeItem);
    this.router.navigate(['/profil/vente']);
  }

  suppres() {
    this.putService.delPost(this.item);
    this.router.navigate(['/profil/vente']);
  }
}
