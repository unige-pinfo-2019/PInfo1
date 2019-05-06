import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FiltersComponent } from '../filters/filters.component';

@Component({
  selector: 'app-body-catalogue',
  templateUrl: './body-catalogue.component.html',
  styleUrls: ['./body-catalogue.component.scss']
})
export class BodyCatalogueComponent implements OnInit, AfterViewInit {
  @ViewChild(FiltersComponent) child;
  message = "hello world";

  constructor() { }

  ngOnInit() {
  }

  receiveMessage($event){
    this.message = $event;
  }

}
