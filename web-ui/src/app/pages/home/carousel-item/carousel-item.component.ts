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

  items: Item = {"id": "", "usrId": "","name": "","prize": 0,"category": "","description": "",
          "state": "","image": "","report": 0,"date": 0};
  private list_items: any[] = [this.items, this.items, this.items, this.items, this.items, this.items];
  private list_id: any[];


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    this.catalogueService.get_highlight().subscribe((res: any[]) => {
      this.list_id = res;
      console.log(this.list_id)
      for (let entry of this.list_id) {
        console.log(entry);
        this.catalogueService.get_item(entry).subscribe((res: any[]) => {
          this.list_items.push(res[0]);
          this.list_items.splice(0, 1);
        })
      }
      console.log(this.list_items);
  })
  }

}
