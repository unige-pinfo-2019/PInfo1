import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'


@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss']
})
export class FiltersComponent implements OnInit {

  selectedCat : string = "all";
  selectedState : string = "1";
  message: string;


  @Output() messageEvent = new EventEmitter<string>();

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
  }

  selectChangeHandlerCat(event: any) {
    this.selectedCat = event.target.value;
    console.log(this.selectedCat);
  }

  sendMesage(){

    this.messageEvent.emit("?category="+this.selectedCat+"&state="+this.selectedState);
    this.catalogueService.changeMessage("?category="+this.selectedCat+"&state="+this.selectedState)

  }

  selectChangeHandlerState(event: any) {
    this.selectedState = event.target.value;
    console.log(this.selectedState);
  }

}
