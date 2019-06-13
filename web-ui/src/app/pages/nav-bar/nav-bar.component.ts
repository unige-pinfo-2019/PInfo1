import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  public keycloakAuth: KeycloakInstance;

  firstName: String = "";

  constructor(public keycloak: KeycloakService){ }

  ngOnInit() {
    this.firstName =  this.keycloak.getFirstName();
  }

  logOut()  {
    this.keycloak.logout();
  }


}
