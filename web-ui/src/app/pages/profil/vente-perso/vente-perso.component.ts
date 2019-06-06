import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Item } from '../../../models/Item.model'


@Component({
  selector: 'app-vente-perso',
  templateUrl: './vente-perso.component.html',
  styleUrls: ['./vente-perso.component.scss']
})
export class VentePersoComponent implements OnInit {

  private list_items_user: any[];
  //message: string = "?category=all"; --->
  message: string = "1234";

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    this.catalogueService.currentMessage_usrid.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_item_by_user(this.message).subscribe((res: any[]) => {
      this.list_items_user = res;
      console.log(res);
  })})
  }

  // suppres(item: Item){
  //   console.log(item);
  // }

}
