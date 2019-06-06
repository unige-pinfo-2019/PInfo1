import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-info-perso',
  templateUrl: './info-perso.component.html',
  styleUrls: ['./info-perso.component.scss']
})
export class InfoPersoComponent implements OnInit {

  //info_user: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};
  info_user: any = {"id": "", "first_name": "","last_name": "","user_name": "","email": ""};
  message: string = "1234";


  constructor(private catalogueService: CatalogueService, public keycloak: KeycloakService) { }

  ngOnInit() {

    /*
    this.catalogueService.currentMessage_info_usr.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_user(this.message).subscribe((res: any[]) => {
      this.info_user = res[0];
      console.log(this.info_user);
  }
)
}
)*/

  this.info_user.id = this.keycloak.getKeycloakAuth().subject;
  this.info_user.first_name = this.keycloak.getFirstName();
  this.info_user.last_name = this.keycloak.getLastName();
  this.info_user.user_name = this.keycloak.getUsername();
  this.info_user.email = this.keycloak.getEmail();
  console.log(this.info_user);

  }

}
