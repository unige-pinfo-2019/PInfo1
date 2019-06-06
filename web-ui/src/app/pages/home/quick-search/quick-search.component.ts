import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { CatalogueService } from '../../../services/catalogue.service'

@Component({
  selector: 'app-quick-search',
  templateUrl: './quick-search.component.html',
  styleUrls: ['./quick-search.component.scss']
})
export class QuickSearchComponent implements OnInit {
  selectedCat : string = "all";
  selectedState : string = "all";
  message: string;
  keyword : string ="";
  priceTo : string ="1000000";
  priceFrom : string = "0";

  @Output() messageEvent = new EventEmitter<string>();


  constructor(private catalogueService: CatalogueService) { }

  ngOnInit() {
    //let item = this.http.get('localhost:8080/s/1?keyword=velo');
    //item.subscribe(() => console.log('got the response'));
  }

  onKeyword(event){
    this.keyword = event.target.value;
    console.log(this.keyword)
  }

  selectChangeHandlerCat(event: any) {
    this.selectedCat = event.target.value;
    console.log(this.selectedCat);
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

  sendMesage(){
    this.messageEvent.emit("?keyword="+this.keyword+"&category="+this.selectedCat+"&state="+this.selectedState+"&sprice="+this.priceFrom+"&fprice="+this.priceTo);
    this.catalogueService.changeMessage("?keyword="+this.keyword+"&category="+this.selectedCat+"&state="+this.selectedState+"&sprice="+this.priceFrom+"&fprice="+this.priceTo)

  }

}
