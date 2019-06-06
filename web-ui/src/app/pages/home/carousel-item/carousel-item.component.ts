import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router'
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Item } from '../../../models/Item.model'


@Component({
  selector: 'app-carousel-item',
  templateUrl: './carousel-item.component.html',
  styleUrls: ['./carousel-item.component.scss']
})
export class CarouselItemComponent implements OnInit {

  items: Item = {"id": "", "usrId": "","name": "","price": 0,"category": "","description": "",
          "state": "","images": "","report": 0,"date": 0,"sold": false};
  private itemCat: any[][] = [[],[],[]];
  private list_items: any[] = [this.items, this.items, this.items, this.items, this.items, this.items];
  private itemCat1: any[] = [this.items];
  private itemCat2: any[] = [];
  private itemCat3: any[] = [];

  private list_id: any[];
  private list_cat: any[] = ["","",""];



  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_highCat().subscribe((res: any[]) => {
      this.list_cat = res;
      for (let entry of [0,1,2]) {
          this.catalogueService.get_highCatItem(this.list_cat[entry]).subscribe((res2: any[]) => {
            for (let i in res2) {
              this.catalogueService.get_item(res2[i]).subscribe((res3: any[]) => {
                this.itemCat[entry].push(res3[0]);

              })
            }
          })
        }
    });
  }

}
