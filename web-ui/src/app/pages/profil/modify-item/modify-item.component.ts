import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { PostService } from '../../../services/post.service'
import { CatalogueService } from '../../../services/catalogue.service'
import { Observable, Subscription } from 'rxjs';
import { Router } from '@angular/router'

@Component({
  selector: 'app-modify-item',
  templateUrl: './modify-item.component.html',
  styleUrls: ['./modify-item.component.scss']
})
export class ModifyItemComponent implements OnInit {

  constructor(private catalogueService: CatalogueService, private postService: PostService, private router: Router) { }

  postForm : FormGroup;
  usrId : number = 0;
  name : string = "";
  name_boolean : boolean = false;
  price : number= 0;
  price_boolean : boolean = false;
  category : string = "";
  category_boolean : boolean = false;
  description : string = "";
  description_boolean : boolean = false;
  state : number = 0;
  state_boolean : boolean = false;

  message: string = "1234";
  info_item: any = {"usrId": 0, "name": "","price": 0,"category": "","description": "","state": 0};

  ngOnInit() {
    this.catalogueService.currentMessage_info_item.subscribe((res) => {
    this.message = res;
    this.catalogueService.get_item(this.message).subscribe((res: any[]) => {
      this.info_item = res[0];
      console.log("la:"+this.info_item.usrId);
      this.usrId = this.info_item.id;
      this.name = this.info_item.name;
      this.price = this.info_item.price;
      this.category = this.info_item.category;
      this.description = this.info_item.description;
      this.state = this.info_item.state;
  })})
  }

  newInfo() {
    this.info_item.name = this.name;
    this.info_item.price = this.price;
    this.info_item.category = this.category;
    this.info_item.description = this.description;
    this.info_item.state = this.state;
    console.log(this.info_item);
    this.catalogueService.changeItem(this.info_item.usrId);
  }

  set_name(event) {
    this.name = event.target.value;
    if(event.target.value=="0"){
      this.name_boolean=false;
    }else{
      this.name_boolean=true;
    }
  }

  set_price(event) {
    this.price = event.target.value;
    if(event.target.value=="0"){
      this.price_boolean=false;
    }else{
      this.price_boolean=true;
    }
  }

  set_category(event) {
    this.category = event.target.value;
    if(event.target.value=="0"){
      this.category_boolean=false;
    }else{
      this.category_boolean=true;
    }
  }

  set_description(event) {
    this.description = event.target.value;
    if(event.target.value=="0"){
      this.description_boolean=false;
    }else{
      this.description_boolean=true;
    }
  }

  set_state(event) {
    this.state = event.target.value;
    if(event.target.value=="0"){
      this.state_boolean=false;
    }else{
      this.state_boolean=true;
    }
  }


  onSubmitForm() {
    this.postService.modifyItem(this.usrId, this.name, this.price, this.category, this.description, this.state);
    this.router.navigate(['../profil/vente']);
  }

}
