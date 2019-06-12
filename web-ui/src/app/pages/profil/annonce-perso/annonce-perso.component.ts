import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service';
import { Annonce } from '../../../models/Item.model';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';




@Component({
  selector: 'app-annonce-perso',
  templateUrl: './annonce-perso.component.html',
  styleUrls: ['./annonce-perso.component.scss']
})
export class AnnoncePersoComponent implements OnInit {

  private list_annonce_user: Annonce[];

  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService) { }

  ngOnInit() {
    this.catalogueService.get_annonce_by_user(this.keycloak.getKeycloakAuth().subject).subscribe((res: Annonce[]) => {
      this.list_annonce_user = res;
  })
  }

}
