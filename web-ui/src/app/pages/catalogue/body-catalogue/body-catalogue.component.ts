import { Component, OnInit, ViewChild, Output } from '@angular/core';
import { FiltersComponent } from '../filters/filters.component';

@Component({
  selector: 'app-body-catalogue',
  templateUrl: './body-catalogue.component.html',
  styleUrls: ['./body-catalogue.component.scss']
})
export class BodyCatalogueComponent implements OnInit {
  @ViewChild(FiltersComponent) child;
  message = "mobilier";

  constructor() { }

  ngOnInit() {
  }

  receiveMessage($event){
    this.message = $event;
  }

}
