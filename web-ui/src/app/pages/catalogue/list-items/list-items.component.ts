import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-items',
  templateUrl: './list-items.component.html',
  styleUrls: ['./list-items.component.scss']
})
export class ListItemsComponent implements OnInit {

  constructor() { }

  item_list = [
        {
            title: 'eXtreme Programming Explained'
        },
        {
            title: 'Clean Code'
        },
        {
            title: 'Clean Code'
        },
        {
            title: 'Clean Code'
        }
    ];

  ngOnInit() {
  }

}
