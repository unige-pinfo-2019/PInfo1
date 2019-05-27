import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-page-profil',
  templateUrl: './page-profil.component.html',
  styleUrls: ['./page-profil.component.scss']
})
export class PageProfilComponent implements OnInit {

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

}
