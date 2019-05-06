import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router'
import { CatalogueService } from '../../../services/catalogue.service'

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.scss']
})
export class ListItemsComponent implements OnInit {

  list_items: any[];

  constructor(private catalogueService: CatalogueService, private router: Router) { }

  item_list = [
        [
            'eXtreme Programming Explained',
            1
        ],
        [
            'Clean Code'
        ],
        [
            'Clean Code'
        ],
        [
            'Clean Code'
        ]
  ];

  ngOnInit() {
    this.list_items = this.catalogueService.list;
    console.log(this.list_items);

    this.list_items = this.catalogueService.displayCatalogue();
    //this.list_items = this.catalogueService.list;
    console.log("list items:"+this.list_items);
    return this.list_items
  }

}
