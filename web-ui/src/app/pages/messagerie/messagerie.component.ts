import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CatalogueService } from '../../services/catalogue.service';
import { KeycloakService } from '../../services/keycloak/keycloak.service';
import { User } from '../../models/Item.model';



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
      for (var el in this.list_discussion) {
        var corres = this.list_discussion[el][0][0];
        if (corres == this.keycloak.getKeycloakAuth().subject){
          corres = this.list_discussion[el][0][1];
        }
        this.catalogueService.get_user(corres).subscribe((res2: User) => {
          this.list_discussion[el][0].push(res2.name + " " +res2.surname);
          if (res2.image == "" || res2.image == null){
            this.list_discussion[el][0].push("Acq7kkx");
          } else {
            this.list_discussion[el][0].push(res2.image);
          }
          this.list_discussion[el][0].push(corres);

        });
      }
      console.log(this.list_discussion);

      this.longueur = this.list_discussion.length;
    })
  }
}
