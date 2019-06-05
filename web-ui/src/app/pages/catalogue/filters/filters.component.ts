import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'


@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.scss']
})
export class FiltersComponent implements OnInit {

  selectedCat : string = "all";
  selectedState : string = "all";
  message: string;
  keyword : string ="";
  priceTo : string ="1000000";
  priceFrom : string = "0";
  oldText: string = "";

  @Output() messageEvent = new EventEmitter<string>();

  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    this.catalogueService.currentMessage.subscribe((res) => {
    this.oldText = res;
  })
  }

  parse(text){
    
  }

  onKeyword(event){
    this.keyword = event.target.value;
    console.log(this.keyword)
  }

  selectChangeHandlerCat(event: any) {
    this.selectedCat = event.target.value;
    console.log(this.selectedCat);
  }

  sendMesage(){
    this.messageEvent.emit("?keyword="+this.keyword+"&category="+this.selectedCat+"&state="+this.selectedState+"&sprice="+this.priceFrom+"&fprice="+this.priceTo);
    this.catalogueService.changeMessage("?keyword="+this.keyword+"&category="+this.selectedCat+"&state="+this.selectedState+"&sprice="+this.priceFrom+"&fprice="+this.priceTo)

  }

  selectChangeHandlerState(event: any) {
    this.selectedState = event.target.value;
    console.log(this.selectedState);
  }

  onPriceTo(event){
    this.priceTo = event.target.value;
  }

  onPriceFrom(event){
    this.priceFrom = event.target.value;
  }


}
