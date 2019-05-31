import { Component, OnDestroy, OnInit } from '@angular/core';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInstance } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit{
  title = 'web-ui';
   public keycloakAuth: KeycloakInstance;

   constructor(public keycloak: KeycloakService) {


 }

ngOnInit() {
  console.log("ON inti");
        this.keycloakAuth = this.keycloak.getKeycloakAuth();
        if (this.keycloak.isLoggedIn() === false) {
            this.keycloak.login();
            console.log("connexion essaye")
          }
        if (this.keycloak.isLoggedIn() == true){
          console.log("connecter");
          console.log(this.keycloakAuth);
        }
}
}
