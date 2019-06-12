  import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CatalogueService } from '../../services/catalogue.service'
import { Item, User } from '../../models/Item.model';


@Component({
  selector: 'app-page-achat',
  templateUrl: './page-achat.component.html',
  styleUrls: ['./page-achat.component.scss']
})
export class PageAchatComponent implements OnInit {

  id: string;
  items: Item = {"id": "", "usrId": "","name": "","price": 0,"category": "","description": "",
          "state": "","images": "","report": 0,"date": 0,"sold": false};
  prix: string = "0";
  image: string;
  refImage: string;
  destinataire: User;

  constructor(private catalogueService: CatalogueService, private router: Router) { }

  ngOnInit() {
    var str = this.router.url;
    this.id = str.split("/",9).pop();
    this.catalogueService.get_item(this.id).subscribe((res: any[]) => {
      this.items = res[0];
      this.catalogueService.get_user(this.items.usrId).subscribe((res: User) => {
          this.destinataire = res;
    });
      if (this.items.images != "") {
        this.image = "https://i.imgur.com/"+this.items.images+".jpg";
        this.refImage = "https://imgur.com/"+this.items.images;
      }
    })
  }

  acheter(){
    this.router.navigate(['/profil/achat']);
  }

  message(){
    this.router.navigate(['../../../discussion/'+this.items.usrId]);

  }

}
