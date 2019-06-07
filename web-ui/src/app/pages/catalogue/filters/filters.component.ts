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
  recherche: string[] = ["Search","","","",""];

  @Output() messageEvent = new EventEmitter<string>();

  constructor(private catalogueService: CatalogueService) { }

  parse(text: string){
    var splitted = text.split("?",2);
    console.log(splitted[1]);
    var separe = 2;
    if (splitted[1].search("sprice") == -1 ) {
      separe = 2;
    } else {
      separe = 5;
    }
    console.log(separe);
    var attribut = splitted[1].split("&",separe);
    for (var att in attribut) {
      var rech = attribut[att].split("=",2);
      if (separe == 2) {
        this.recherche[att+1] = rech[1];
      } else {
        this.recherche[att] = rech[1];
      }
    }
    // this.selectedCat=this.recherche[2]
  }

  ngOnInit() {
    this.catalogueService.currentMessage.subscribe((res) => {
    this.oldText = res;
    this.parse(this.oldText);
    
  })
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
