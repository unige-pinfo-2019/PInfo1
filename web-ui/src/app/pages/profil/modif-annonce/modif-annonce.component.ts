import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PutService } from '../../../services/put.service'
import { CatalogueService } from '../../../services/catalogue.service'
import { HttpHeaders, HttpClient ,HttpParams } from '@angular/common/http';
import { Annonce } from '../../../models/Item.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modif-annonce',
  templateUrl: './modif-annonce.component.html',
  styleUrls: ['./modif-annonce.component.scss']
})
export class ModifAnnonceComponent implements OnInit {
  id: string;
  ad: Annonce = {"id": "", "usrId": "","name": "","category": "","desc": "",
          "state": "","sold": "false"};
  changeAd: Annonce = this.ad;



  name_boolean : boolean = true;
  description_boolean : boolean = true;
  categorie_boolean : boolean = true;
  etat_boolean : boolean = true;

  constructor(private catalogueService: CatalogueService, private putService: PutService, private router: Router, private httpClient: HttpClient) { }

  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    console.log(this.id);
    this.catalogueService.get_annonce_by_id(this.id).subscribe((res: Annonce) => {
      this.ad = res;
      this.changeAd = res;
      console.log(this.ad.name+" annonce à modifie : " +this.ad.desc);
    });
  }

  set_name(event){
    this.changeAd.name = event.target.value;
    if(event.target.value==""){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  selectChangeHandlerState(event: any) {
    this.changeAd.state = event.target.value;
    if(event.target.value=="0"){
      this.etat_boolean=false;
    }else{
      this.etat_boolean=true;
    }
  }

  selectChangeHandlerCat(event: any) {
    this.changeAd.category = event.target.value;
    if(event.target.value=="all"){
      this.categorie_boolean=false;
    }else{
      this.categorie_boolean=true;
    }
  }

  set_description(event){
    this.changeAd.desc = event.target.value;
    if(event.target.value==""){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  onSubmitForm() {
    console.log("on veut modifié l'annonce");
    this.putService.modifAd(this.changeAd);
    this.router.navigate(['/profil/annonce']);
  }

  suppres() {
    console.log("On veut supprimer l'annonce");
    this.putService.delAd(this.ad);
    this.router.navigate(['/profil/annonce']);
  }

}
