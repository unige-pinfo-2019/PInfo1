import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatalogueService } from '../../services/catalogue.service'
import { Item } from '../../models/Item.model'

@Component({
  selector: 'app-page-achat',
  templateUrl: './page-achat.component.html',
  styleUrls: ['./page-achat.component.scss']
})
export class PageAchatComponent implements OnInit {

  id: string;
  items: Item = {"id": "", "usrId": "","name": "","prize": 0,"category": "","description": "",
          "state": "","image": "","report": 0,"date": 0};
  prix: string = "0";
  user: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};


  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    console.log(this.id);
    this.catalogueService.get_item(this.id).subscribe((res: any[]) => {
      this.items = res[0];
      console.log(this.items);
      this.catalogueService.get_user(this.items.usrId).subscribe((res: any[]) => {
        if (Array.isArray(res) && res.length) {
          this.user = res[0];
          console.log(this.user);
      // array exists and is not empty
      }
      })
    })
  }

}
