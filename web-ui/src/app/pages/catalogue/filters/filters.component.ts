import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'


@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss']
})
export class FiltersComponent implements OnInit {

  selectedCat : string = "SALUT";
  selectedState : string = '';
  message : string;


  @Output() messageEvent = new EventEmitter<String>();
  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    this.message = this.catalogueService.baseURL;
  }

  selectChangeHandlerCat(event: any) {
    this.selectedCat = event.target.value;
    console.log(this.selectedCat);
  }

  sendMesage(){
    this.messageEvent.emit(this.selectedCat);
    this.catalogueService.changeMessage(this.selectedCat)
    this.message = this.catalogueService.baseURL;

  }

  selectChangeHandlerState(event: any) {
    this.selectedState = event.target.value;
    console.log(this.selectedState);
  }

}
