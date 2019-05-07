import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router'
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.scss']
})
export class ListItemsComponent implements OnInit {

  @Input() paramCat : string = "all";
  postSubscription: Subscription;
  private list_items: any[];
  private listI = new Observable();

  message: string = "all";


  private catalogueObservable : Observable<any[]>;

  constructor(private catalogueService: CatalogueService, private router: Router) { }


  ngOnInit() {
    this.catalogueService.currentMessage.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_catalogue(this.message).subscribe((res: any[]) => {
      this.list_items = res;
  })})
  }

}
