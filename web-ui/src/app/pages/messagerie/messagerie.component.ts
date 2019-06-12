import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../services/catalogue.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';


import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-messagerie',
  templateUrl: './messagerie.component.html',
  styleUrls: ['./messagerie.component.scss']
})
export class MessagerieComponent implements OnInit {
  private list_discussion: any[];
  hisId: string = "";
  longueur: number = 0;


  constructor(private catalogueService: CatalogueService, private router: Router, public keycloak: KeycloakService) { }

  ngOnInit() {
    this.catalogueService.get_messenger(this.keycloak.getKeycloakAuth().subject).subscribe((res: any[]) => {
      this.list_discussion = res;
      this.longueur = this.list_discussion.length;
    })
  }
}
