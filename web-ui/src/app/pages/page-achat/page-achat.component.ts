  import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatalogueService } from '../../services/catalogue.service'
import { Item, User } from '../../models/Item.model'
import { KeycloakService } from '../../services/keycloak/keycloak.service';

@Component({
  selector: 'app-page-achat',
  templateUrl: './page-achat.component.html',
  styleUrls: ['./page-achat.component.scss']
})
export class PageAchatComponent implements OnInit {

  id: string;
  items: Item = {"id": "", "usrId": "","name": "","price": 0,"category": "","description": "",
          "state": "","images": "","report": 0,"date": 0,"sold": false};
  prix: string = "0";
  image: string;
  refImage: string;
  destinataire: User = {"id": "", "name": "","surname": "","report": 0,"email": "", "image": "", "userReport": ""};

  constructor(private catalogueService: CatalogueService, private router: Router,  public keycloak: KeycloakService) { }

  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    this.catalogueService.get_item(this.id,this.keycloak.getKeycloakAuth().subject).subscribe((res: any[]) => {
      this.items = res[0];
      this.catalogueService.get_user(this.items.usrId).subscribe((res: User) => {
          this.destinataire = res;
    });
      if (this.items.images != "") {
        this.image = "https://i.imgur.com/"+this.items.images+".jpg";
        this.refImage = "https://imgur.com/"+this.items.images;
      }
    })
  }


  message(){
    this.router.navigate(['../../../discussion/'+this.items.usrId]);

  }

}
