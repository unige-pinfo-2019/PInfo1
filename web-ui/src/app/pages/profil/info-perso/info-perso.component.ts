import { Component, OnInit } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-info-perso',
  templateUrl: './info-perso.component.html',
  styleUrls: ['./info-perso.component.scss']
})
export class InfoPersoComponent implements OnInit {

  info_user: any = {"id": 0, "name": "","surname": "","username": "","email": "","report": 0,"grade": 0};
  message: string = "1234";

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    this.catalogueService.currentMessage_info_usr.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_user(this.message).subscribe((res: any[]) => {
      this.info_user = res[0];
      console.log(this.info_user);
  }
)
}
)
  }

}
