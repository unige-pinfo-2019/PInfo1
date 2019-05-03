import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-quick-search',
  templateUrl: './quick-search.component.html',
  styleUrls: ['./quick-search.component.scss']
})
export class QuickSearchComponent implements OnInit {
  item = "";

  constructor() { }

  ngOnInit() {
    //let item = this.http.get('localhost:8080/s/1?keyword=velo');
    //item.subscribe(() => console.log('got the response'));
  }

}
