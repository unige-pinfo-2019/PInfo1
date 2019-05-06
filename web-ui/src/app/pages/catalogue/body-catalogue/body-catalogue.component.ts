import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { FiltersComponent } from '../filters/filters.component';

@Component({
  selector: 'app-body-catalogue',
  templateUrl: './body-catalogue.component.html',
  styleUrls: ['./body-catalogue.component.scss']
})
export class BodyCatalogueComponent implements OnInit, AfterViewInit {
  @ViewChild(FiltersComponent) child;
  messageFromFilter: string;
  message = "la";

  constructor() { }

  ngOnInit() {
  }

  ngAfterViewInit(){
    this.messageFromFilter = this.child.selectedCat;
  }

}
