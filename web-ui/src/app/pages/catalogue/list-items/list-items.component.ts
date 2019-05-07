import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router'
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable } from 'rxjs';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.scss']
})
export class ListItemsComponent implements OnInit {

  @Input() paramCat : string = "all";

  private list_items = [];

  private catalogueObservable : Observable<any[]>;

  constructor(private catalogueService: CatalogueService, private router: Router) { }


  ngOnInit() {
    this.catalogueService.get_catalogue().subscribe((res: any[]) => {
      console.log(res);
      this.list_items = res;
  })
  }

}
