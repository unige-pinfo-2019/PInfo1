import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Item } from '../../../models/Item.model'
import { KeycloakService } from '../../../services/keycloak/keycloak.service';


@Component({
  selector: 'app-vente-perso',
  templateUrl: './vente-perso.component.html',
  styleUrls: ['./vente-perso.component.scss']
})
export class VentePersoComponent implements OnInit {

  private list_items_user: any[];
  //message: string = "?category=all"; --->
  //message: string = "1234";
  message = this.keycloak.getKeycloakAuth().subject;

  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService) { }

  ngOnInit() {
    this.catalogueService.currentMessage_usrid.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_item_by_user(this.message).subscribe((res: any[]) => {
      this.list_items_user = res;
  })})
  }

}
