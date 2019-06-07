import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service';
import { Annonce } from '../../../models/Item.model'



@Component({
  selector: 'app-annonce-perso',
  templateUrl: './annonce-perso.component.html',
  styleUrls: ['./annonce-perso.component.scss']
})
export class AnnoncePersoComponent implements OnInit {

  private list_annonce_user: Annonce[];

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    // modifier l'id !!!!!!!!!!
    this.catalogueService.get_annonce_by_user("1234").subscribe((res: Annonce[]) => {
      this.list_annonce_user = res;
      console.log(res);
  })
  }

}
