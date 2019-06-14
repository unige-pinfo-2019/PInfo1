import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatalogueService } from '../../services/catalogue.service'
import { User, Annonce } from '../../models/Item.model'
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-page-annonce',
  templateUrl: './page-annonce.component.html',
  styleUrls: ['./page-annonce.component.scss']
})
export class PageAnnonceComponent implements OnInit {

  id: string;
  ad: Annonce = {"id": "", "usrId": "","name": "","category": "","desc": "",
          "state": "","sold": false};

  destinataire: User = {"id": "", "name": "","surname": "","report": 0,"email": "", "image": "", "userReport": ""};
  constructor(private catalogueService: CatalogueService, private router: Router,  public keycloak: KeycloakService) { }

  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    this.catalogueService.get_annonce_by_id(this.id).subscribe((res: Annonce) => {
      this.ad = res;
      this.catalogueService.get_user(this.ad.usrId).subscribe((res: User) => {
          this.destinataire = res;
    });
    })
  }


  message(){
    this.router.navigate(['../../../discussion/'+this.ad.usrId]);

  }

}
